package com.example.orcamentofamiliar.Controllers.Dtos;

import com.example.orcamentofamiliar.Entidades.Categorias;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class CategoriasDto {

    private BigDecimal valorDespesa;
    private Categorias categorias;


    public CategoriasDto(Categorias categorias, BigDecimal valorDespesa) {
        this.categorias = categorias;
        this.valorDespesa = valorDespesa;
    }

    public CategoriasDto() {
    }
}
