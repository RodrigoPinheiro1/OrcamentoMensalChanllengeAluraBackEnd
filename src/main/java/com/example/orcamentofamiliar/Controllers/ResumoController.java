package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.ResumoDto;
import com.example.orcamentofamiliar.Controllers.Forms.TempoResumoForm;
import com.example.orcamentofamiliar.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resumo")
@Profile(value = {"test","prod","dev"})
public class ResumoController {


    @Autowired
    private ResumoService service;



    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<ResumoDto> listarTudoNoMes  (@PathVariable int ano, @PathVariable int mes,
                                                       TempoResumoForm tempoResumoForm){
        ResumoDto resumoDto = service.calcularValores(ano,mes);
        return ResponseEntity.ok(resumoDto);
    }



}
