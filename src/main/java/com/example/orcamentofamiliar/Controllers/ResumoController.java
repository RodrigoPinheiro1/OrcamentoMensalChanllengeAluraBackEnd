package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.Resumo.ResumoDto;
import com.example.orcamentofamiliar.Controllers.Forms.TempoResumoForm;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/resumo")
public class ResumoController {


    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private DespesasRepository  despesasRepository;



    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<ResumoDto> listarTudoNoMes  (@PathVariable int ano, @PathVariable int mes,
                                                       TempoResumoForm tempoResumoForm){



        ResumoDto resumoDto = tempoResumoForm.calcularValores(receitasRepository, despesasRepository, ano, mes);
        return ResponseEntity.ok(resumoDto);
    }



}
