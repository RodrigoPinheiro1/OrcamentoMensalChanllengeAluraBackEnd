package com.example.orcamentofamiliar.Controllers.Dtos;

import com.example.orcamentofamiliar.Entidades.Categorias;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DespesasDto {

    private Long id;
    @NotNull
    private String descricao;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private LocalDate data;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categorias categorias;





}
