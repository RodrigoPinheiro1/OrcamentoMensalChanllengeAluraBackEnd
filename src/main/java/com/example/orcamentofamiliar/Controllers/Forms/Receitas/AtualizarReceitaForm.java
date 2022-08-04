package com.example.orcamentofamiliar.Controllers.Forms.Receitas;

import com.example.orcamentofamiliar.Entidades.Receitas;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Getter
@Setter
public class AtualizarReceitaForm {

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate data;


    public Receitas atualizar(ReceitasRepository receitasRepository, Long id) {

        Receitas receitas = receitasRepository.getReferenceById(id);

        receitas.setDescricao(descricao);
        receitas.setValor(valor);
        receitas.setData(data);

        return receitas;
    }
    public  Boolean verifica(ReceitasRepository receitasRepository) {

        LocalDate firstDay = data.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = data.with(TemporalAdjusters.lastDayOfMonth());

        Optional<Receitas> receitas = receitasRepository.findByDescricaoAndDataBetween(descricao,firstDay,lastDay);


        if (receitas.isPresent()){
            return true;
        }
        return false;
    }


}
