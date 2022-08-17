package com.example.orcamentofamiliar.Controllers.Forms.Despesas;


import com.example.orcamentofamiliar.Entidades.Categorias;
import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Getter
@Setter
public class AtualizarDespesaForm {

    @NotNull
    private String descricao;
    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate data;

    private Categorias categorias;


    public Despesas atualizar(DespesasRepository despesasRepository, Long id) {
        Despesas despesas = despesasRepository.getReferenceById(id);

        despesas.setDescricao(descricao);
        despesas.setValor(valor);
        despesas.setData(data);
        despesas.setCategorias(categorias);

        return despesas;
    }

    public Boolean isRepeated(DespesasRepository despesasRepository, Long id) {

        LocalDate firstDay = data.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = data.with(TemporalAdjusters.lastDayOfMonth());

        return despesasRepository.findByIdNotAndDescricaoAndDataBetween(id, descricao, firstDay, lastDay).isPresent();
    }
}
