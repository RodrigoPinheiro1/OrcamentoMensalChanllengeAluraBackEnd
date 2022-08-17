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
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesasControlller {

    @Autowired
    private DespesasRepository despesasRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarDespesas(@RequestBody @Valid DespesasForm despesasForm,
                                                         UriComponentsBuilder uriComponentsBuilder) {

        if (!despesasForm.isRepeat(despesasRepository)) {
            Despesas despesas = despesasForm.cadastrar();
            despesasRepository.save(despesas);
            URI uri = uriComponentsBuilder.path("/despesas").buildAndExpand(despesas.getId()).toUri();
            return ResponseEntity.created(uri).body(new DespesasDto(despesas));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Despesa existente neste mês");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarDespesaForm atualizarDespesaForm) {

        Optional<Despesas> despesas = despesasRepository.findById(id);
        if (!despesas.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (atualizarDespesaForm.isRepeated(despesasRepository,id)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Despesa ja existe nesse mês");
        }
            atualizarDespesaForm.atualizar(despesasRepository, id);
        return ResponseEntity.ok(new DespesasDto(despesas.get()));
    }
    @GetMapping("/{mes}/{ano}")
    public Page<DespesasDto> listarPorData  (@PathVariable int mes , @PathVariable int ano ,
                                             @PageableDefault(sort = "data"  ,direction = Sort.Direction.ASC
                                             ,page = 0, size = 10) Pageable pageable){


        Page<Despesas> despesas = despesasRepository.acharPorMesEAno(mes, ano, pageable);

        return DespesasDto.converter(despesas);
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

