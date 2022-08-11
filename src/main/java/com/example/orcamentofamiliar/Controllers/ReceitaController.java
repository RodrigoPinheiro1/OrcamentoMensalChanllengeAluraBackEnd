package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Forms.Receitas.AtualizarReceitaForm;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import com.example.orcamentofamiliar.Controllers.Dtos.Receitas.ReceitasDto;
import com.example.orcamentofamiliar.Controllers.Forms.Receitas.ReceitasForm;
import com.example.orcamentofamiliar.Entidades.Receitas;
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
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitasRepository receitasRepository;

    @PostMapping
    public ResponseEntity<ReceitasDto> cadastrar (@RequestBody @Valid ReceitasForm receitasForm,
                                                  UriComponentsBuilder uriComponentsBuilder){

        Receitas receitas = receitasForm.cadastrar();
        Boolean verifica = receitasForm.verifica(receitasRepository);
        if (!verifica) {
            receitasRepository.save(receitas);
            URI uri = uriComponentsBuilder.path("/receitas/{id}").buildAndExpand(receitas.getId()).toUri();
            return ResponseEntity.created(uri).body(new ReceitasDto(receitas));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReceitasDto> atualizarReceita (@PathVariable Long id, @RequestBody @Valid AtualizarReceitaForm atualizarReceitaForm){

        Optional<Receitas> receitas = receitasRepository.findById(id);
        Boolean verifica;
        if (receitas.isPresent()) {
            verifica = atualizarReceitaForm.verifica(receitasRepository);
            if (!verifica) {
                atualizarReceitaForm.atualizar(receitasRepository, id);
                return ResponseEntity.ok(new ReceitasDto(receitas.get()));
            } else if (verifica) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();

            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitasDto> buscarPorId (@PathVariable Long id){

        Optional<Receitas> receitas = receitasRepository.findById(id);

        if (receitas.isPresent()){
            return ResponseEntity.ok(new ReceitasDto(receitas.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public Page<ReceitasDto> listarTudoOuNome (@RequestParam (required = false) String descricao,
                                               @PageableDefault (sort = "id", direction = Sort.Direction.DESC,
                                                       page = 0, size = 10) Pageable page){

        if (descricao == null) {
            Page<Receitas> receitas = receitasRepository.findAll(page);
            return ReceitasDto.converter(receitas);
        }
        Page<Receitas> receitas = receitasRepository.findByDescricao(descricao, page);
        return ReceitasDto.converter(receitas);
    }


    @GetMapping("/{mes}/{ano}")
    public Page<ReceitasDto> listarPorAnoEMes(@PathVariable int  ano  , @PathVariable int mes,
                                              @PageableDefault (sort = "data",direction = Sort.Direction.ASC,
                                              page = 0, size = 10) Pageable pageable){

        Page<Receitas> receitas = receitasRepository.acharDataEMes(mes,ano,pageable);

        return  ReceitasDto.converter(receitas);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> Apaga (@PathVariable Long id) {

        Optional<Receitas> receitas = receitasRepository.findById(id);

        if (receitas.isPresent()){

            receitasRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }


}
