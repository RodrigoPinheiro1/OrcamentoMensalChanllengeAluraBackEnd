package com.example.orcamentofamiliar.Controllers.Dtos.Resumo;

import com.example.orcamentofamiliar.Entidades.Categorias;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ResumoDto {

    private BigDecimal valorReceita;
    private BigDecimal valorDespesas;
    private BigDecimal saldo;
    private Categorias categoria;


    public ResumoDto(BigDecimal valorTotalReceita, BigDecimal valorTotalDespesas, BigDecimal saldo, Categorias categoria) {
        this.valorReceita = valorTotalReceita;
        this.valorDespesas = valorTotalDespesas;
        this.saldo = valorTotalReceita.subtract(valorTotalDespesas);
        this.categoria = categoria;
    }

    public ResumoDto(BigDecimal valorTotalReceita) {
        this.valorReceita = valorTotalReceita;
    }


    public ResumoDto() {

    }
}
