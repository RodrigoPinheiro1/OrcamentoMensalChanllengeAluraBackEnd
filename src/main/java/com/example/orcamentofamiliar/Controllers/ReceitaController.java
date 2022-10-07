package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.ReceitasDto;
import com.example.orcamentofamiliar.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ReceitasDto> cadastrarDespesas(@RequestBody @Valid ReceitasDto dto,
                                                    UriComponentsBuilder uriComponentsBuilder) {

            ReceitasDto receitas = service.cadastro(dto);
            URI uri = uriComponentsBuilder.path("/receitas").buildAndExpand(receitas.getId()).toUri();
            return ResponseEntity.created(uri).body(receitas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> atualizar(@PathVariable Long id,
                                            @RequestBody @Valid ReceitasDto dto) {

        service.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{mes}/{ano}")
    public Page<ReceitasDto> listarPorData(@PathVariable int mes, @PathVariable int ano,
                                           @PageableDefault(sort = "data", direction = Sort.Direction.ASC
                                                   , page = 0, size = 10) Pageable pageable) {
        return service.buscarPelaData(mes, ano, pageable);
    }

    @GetMapping
    @Transactional
    public Page<ReceitasDto> listarTudo(@RequestParam(required = false) String descricao,
                                        @PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                                page = 0, size = 10) Pageable pageable) {

        return service.buscarTudoOuPorNome(descricao, pageable);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ReceitasDto> buscarPorId(@PathVariable Long id) {

            ReceitasDto dto = service.acharPorId(id);
            return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluirPorId(@PathVariable Long id) {

            service.deletarPorId(id);
            return ResponseEntity.noContent().build();
    }


}
