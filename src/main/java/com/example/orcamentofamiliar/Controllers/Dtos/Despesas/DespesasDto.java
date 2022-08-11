package com.example.orcamentofamiliar.Controllers.Dtos.Despesas;

import com.example.orcamentofamiliar.Entidades.Categorias;
import com.example.orcamentofamiliar.Entidades.Despesas;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class DespesasDto {

    private String descricao;

    private BigDecimal valor;

    private LocalDate data;

    private Categorias categorias;


    public DespesasDto(Despesas despesas) {
        this.descricao = despesas.getDescricao();
        this.valor = despesas.getValor();
        this.data = despesas.getData();
        this.categorias = despesas.getCategorias();
    }


    public static Page<DespesasDto> converter(Page<Despesas> despesas) {

        return  despesas.map(DespesasDto::new);
    }
}
