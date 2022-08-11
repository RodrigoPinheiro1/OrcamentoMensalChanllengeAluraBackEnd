package com.example.orcamentofamiliar.Controllers.Dtos.Resumo;

import com.example.orcamentofamiliar.Controllers.Dtos.CategoriasDto;
import com.example.orcamentofamiliar.Entidades.Categorias;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ResumoDto {

    private BigDecimal valorReceita;
    private BigDecimal valorDespesas;
    private BigDecimal saldo;
    private List<CategoriasDto> categoria;


    public ResumoDto(BigDecimal valorTotalReceita, BigDecimal valorTotalDespesas, List<CategoriasDto> categoria) {
        this.valorReceita = valorTotalReceita;
        this.valorDespesas = valorTotalDespesas;
        this.saldo = valorTotalReceita.subtract(valorTotalDespesas);
        this.categoria = categoria;
    }

    public ResumoDto() {

    }
}
