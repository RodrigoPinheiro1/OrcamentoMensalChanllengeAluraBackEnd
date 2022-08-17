package com.example.orcamentofamiliar.Controllers.Forms.Receitas;

import com.example.orcamentofamiliar.Entidades.Receitas;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Getter
@Setter
public class ReceitasForm {

    @NotEmpty
    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate data;



    public Receitas cadastrar() {

        return new Receitas(descricao, valor, data);
    }


    public Boolean isRepeat(ReceitasRepository receitasRepository) {

        LocalDate firstDay = data.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = data.with(TemporalAdjusters.lastDayOfMonth());

        return receitasRepository.findByDescricaoAndDataBetween(descricao, firstDay, lastDay).isPresent();

    }

}
