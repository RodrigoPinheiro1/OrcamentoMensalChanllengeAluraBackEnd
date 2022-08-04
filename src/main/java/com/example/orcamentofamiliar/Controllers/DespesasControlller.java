package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.Despesas.DespesasDto;
import com.example.orcamentofamiliar.Controllers.Forms.Despesas.AtualizarDespesaForm;
import com.example.orcamentofamiliar.Controllers.Forms.Despesas.DespesasForm;
import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.lang.management.LockInfo;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesasControlller {


    @Autowired
    private DespesasRepository despesasRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DespesasDto> cadastrarDespesas(@RequestBody @Valid DespesasForm despesasForm,
                                                         UriComponentsBuilder uriComponentsBuilder) {

        Boolean verifica = despesasForm.verifica(despesasRepository);
        if (!verifica) {
            Despesas despesas = despesasForm.cadastrar();
            despesasRepository.save(despesas);
            URI uri = uriComponentsBuilder.path("/despesas").buildAndExpand(despesas.getId()).toUri();
            return ResponseEntity.created(uri).body(new DespesasDto(despesas));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DespesasDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarDespesaForm atualizarDespesaForm) {

        Optional<Despesas> despesas = despesasRepository.findById(id);
        Boolean verifica;
        if (despesas.isPresent()) {
             verifica =  atualizarDespesaForm.verifica(despesasRepository);
             if (!verifica) {
                 atualizarDespesaForm.atualizar(despesasRepository, id);
                 return ResponseEntity.ok(new DespesasDto(despesas.get()));
             }else if (verifica){
                 return ResponseEntity.status(HttpStatus.CONFLICT).build();
             }
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping
    @Transactional
    public Page<DespesasDto> listarTudo(@RequestParam(required = false) String descricao,
                                        @PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                                page = 0, size = 10) Pageable pageable) {
        if (descricao == null) {
            Page<Despesas> despesas = despesasRepository.findAll(pageable);
            return DespesasDto.converter(despesas);
        }
        Page<Despesas> despesas = despesasRepository.findByDescricao(descricao, pageable);
        return DespesasDto.converter(despesas);

    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DespesasDto> buscarPorId(@PathVariable Long id) {

        Optional<Despesas> despesas = despesasRepository.findById(id);
        if (despesas.isPresent()) {

            return ResponseEntity.ok(new DespesasDto(despesas.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> ExcluirPorId(@PathVariable Long id) {

        Optional<Despesas> despesas = despesasRepository.findById(id);

        if (despesas.isPresent()) {
            despesasRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
      return ResponseEntity.notFound().build();

    }


}

