package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.ReceitasDto;
import com.example.orcamentofamiliar.service.ReceitaService;
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
@RequestMapping("/receitas")
@Profile(value = {"test", "prod", "dev"})
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrarDespesas(@RequestBody @Valid ReceitasDto dto,
                                                    UriComponentsBuilder uriComponentsBuilder) {

        if (Boolean.FALSE.equals(service.InsertIsRepeat(dto))) {
            ReceitasDto receitas = service.cadastro(dto);

            URI uri = uriComponentsBuilder.path("/receitas").buildAndExpand(receitas.getId()).toUri();
            return ResponseEntity.created(uri).body(receitas);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Despesa existente neste mês");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> atualizar(@PathVariable Long id,
                                            @RequestBody @Valid ReceitasDto dto) {

        if (service.UpdateIsRepeated(id, dto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Despesa ja existe nesse mês");
        }
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

        if (service.isFound(id)) {
            ReceitasDto dto = service.acharPorId(id);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> ExcluirPorId(@PathVariable Long id) {

        if (service.isFound(id)) {
            service.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
