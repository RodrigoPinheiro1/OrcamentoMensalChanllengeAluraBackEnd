package com.example.orcamentofamiliar.Controllers.Forms.Despesas;


import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Entidades.Receitas;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Getter
@Setter
public class AtualizarDespesaForm {

    @NotNull
    private String descricao;
    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate data;



    public Despesas atualizar(DespesasRepository despesasRepository, Long id) {
        Despesas despesas = despesasRepository.getReferenceById(id);

        despesas.setDescricao(descricao);
        despesas.setValor(valor);
        despesas.setData(data);

        return despesas;
    }

    public Boolean verifica(DespesasRepository despesasRepository) {

        LocalDate firstDay = data.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = data.with(TemporalAdjusters.lastDayOfMonth());

        Optional<Despesas> receitas = despesasRepository.findByDescricaoAndDataBetween(descricao,firstDay,lastDay);


        if (receitas.isPresent()){
            return true;
        }
        return false;
    }
}
