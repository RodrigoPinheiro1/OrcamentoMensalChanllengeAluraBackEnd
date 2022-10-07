package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.DespesasDto;
import com.example.orcamentofamiliar.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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

@RestController
@RequestMapping("/despesas")
@Profile(value = {"test", "prod", "dev"})
public class DespesasControlller {

    @Autowired
    private DespesaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DespesasDto> cadastrarDespesas(@RequestBody @Valid DespesasDto dto,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        DespesasDto despesas = service.cadastro(dto);
        URI uri = uriComponentsBuilder.path("/despesas").buildAndExpand(despesas.getId()).toUri();
        return ResponseEntity.created(uri).body(despesas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DespesasDto> atualizar(@PathVariable Long id,
                                                 @RequestBody @Valid DespesasDto dto) {

        service.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{mes}/{ano}")
    public Page<DespesasDto> listarPorData(@PathVariable int mes, @PathVariable int ano,
                                           @PageableDefault(sort = "data", direction = Sort.Direction.ASC
                                                   , page = 0, size = 10) Pageable pageable) {
        return service.buscarPelaData(mes, ano, pageable);

    }

    @GetMapping
    @Transactional
    public Page<DespesasDto> listarTudo(@RequestParam(required = false) String descricao,
                                        @PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                                page = 0, size = 10) Pageable pageable) {

        return service.buscarTudoOuPorNome(descricao, pageable);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DespesasDto> buscarPorId(@PathVariable Long id) {

        DespesasDto dto = service.isFound(id);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> ExcluirPorId(@PathVariable Long id) {

        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }


}

