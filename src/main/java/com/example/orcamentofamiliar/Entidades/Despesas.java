package com.example.orcamentofamiliar.Entidades;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Despesas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Categorias categorias= Categorias.OUTRAS;

    public Despesas() {
    }

    public Despesas(String descricao, BigDecimal valor, LocalDate data , Categorias categorias) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categorias  =  categorias;
    }

    public Despesas(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }
}
