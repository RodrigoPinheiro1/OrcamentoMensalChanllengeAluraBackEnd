package com.example.orcamentofamiliar.Controllers.Forms.Despesas;

import com.example.orcamentofamiliar.Entidades.Categorias;
import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Getter
public class DespesasForm {

    @NotNull
    @NotEmpty
    private String descricao;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate data;
    private Categorias  categorias = Categorias.OUTRAS;

    public Despesas cadastrar() {
        return new Despesas(descricao, valor, data, categorias);
    }

    public Boolean isRepeat(DespesasRepository despesasRepository) {
        LocalDate firstDay = data.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = data.with(TemporalAdjusters.lastDayOfMonth());

        return despesasRepository.findByDescricaoAndDataBetween(descricao, firstDay, lastDay).isPresent();
    }
}
