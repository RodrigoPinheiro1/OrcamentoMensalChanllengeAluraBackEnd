package com.example.orcamentofamiliar.Controllers;

import com.example.orcamentofamiliar.Controllers.Dtos.Resumo.ResumoDto;
import com.example.orcamentofamiliar.Entidades.Receitas;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;


@RestController
@RequestMapping("/resumo")
public class ResumoController {


    @Autowired
    private ReceitasRepository receitasRepository;

    @GetMapping("/{ano}/{mes}")
    public ResumoDto listarTudoNoMes  (@PathVariable int ano, @PathVariable int mes){

        LocalDate date = LocalDate.of(ano,mes,1);

        LocalDate first = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());

            Optional<BigDecimal> valor = receitasRepository.calcularValor(first,last);
            return new ResumoDto(valor.orElse(BigDecimal.ZERO));
    }


}
