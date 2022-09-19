package com.example.orcamentofamiliar.service;

import com.example.orcamentofamiliar.Controllers.Dtos.CategoriasDto;
import com.example.orcamentofamiliar.Controllers.Dtos.ResumoDto;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class ResumoService {

    private LocalDate firstDay;
    private LocalDate lastDay;

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private DespesasRepository despesasRepository;


    public ResumoDto calcularValores(int ano, int mes) {

        calcularTempo(ano, mes);
        Optional<BigDecimal> valorReceitas = receitasRepository.calcularValor(firstDay,lastDay);
        Optional<BigDecimal> valorDespesa = despesasRepository.calcularValor(firstDay, lastDay);
        List<CategoriasDto> categorias = despesasRepository.acharValorCategorias(firstDay, lastDay);

        return new ResumoDto(valorReceitas.orElse(BigDecimal.ZERO), valorDespesa.orElse(BigDecimal.ZERO),categorias);
    }

    public  void calcularTempo(int ano ,int mes) {
        LocalDate date = LocalDate.of(ano, mes, 1);
        firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        lastDay = date.with(TemporalAdjusters.lastDayOfMonth());

    }

}
