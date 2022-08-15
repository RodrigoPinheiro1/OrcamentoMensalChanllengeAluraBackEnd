package com.example.orcamentofamiliar.Controllers.Dtos;

import com.example.orcamentofamiliar.Entidades.Categorias;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategoriasDto {


    private Categorias categorias;
    private BigDecimal valorDaCategoria;


    public CategoriasDto(Categorias categorias, BigDecimal valorDaCategoria) {
        this.categorias = categorias;
        this.valorDaCategoria = valorDaCategoria;
    }

    public CategoriasDto() {
    }
}
