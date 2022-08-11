package com.example.orcamentofamiliar.Controllers.Dtos.Receitas;

import com.example.orcamentofamiliar.Entidades.Receitas;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
public class ReceitasDto {

    private String descricao;

    private BigDecimal valor;

    private  LocalDate data;




    public ReceitasDto(Receitas receitas) {
        this.descricao = receitas.getDescricao();
        this.valor = receitas.getValor();
        this.data = receitas.getData();
    }

    public static Page<ReceitasDto> converter(Page<Receitas> receitas) {

        return receitas.map(ReceitasDto::new);

    }



}
