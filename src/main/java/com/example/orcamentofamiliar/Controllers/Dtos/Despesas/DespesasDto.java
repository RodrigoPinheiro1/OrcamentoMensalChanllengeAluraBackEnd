package com.example.orcamentofamiliar.Controllers.Dtos.Despesas;

import com.example.orcamentofamiliar.Entidades.Categorias;
import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import lombok.*;
import org.springframework.data.domain.Page;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DespesasDto {

    private Long id;
    private String descricao;

    private BigDecimal valor;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Categorias categorias;





}
