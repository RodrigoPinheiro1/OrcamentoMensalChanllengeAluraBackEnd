package com.example.orcamentofamiliar.Repository;

import com.example.orcamentofamiliar.Controllers.Dtos.CategoriasDto;
import com.example.orcamentofamiliar.Entidades.Categorias;
import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Entidades.Receitas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DespesasRepository  extends JpaRepository<Despesas,Long> {
   Page<Despesas> findByDescricao(String descricao, Pageable pageable);

    Optional<Despesas> findByDescricaoAndDataBetween(String descricao, LocalDate firstDay, LocalDate lastDay);

      @Query("select d from Despesas d where MONTH(d.data) = ?1 and YEAR(d.data) = ?2")
    Page<Despesas> acharPorMesEAno(int month, int year, Pageable pageable);

    Optional <Despesas> findByIdNotAndDescricaoAndDataBetween(Long id, String desc, LocalDate inicioMes, LocalDate fimMes);

    @Query (value = "select sum(r.valor) from Despesas r " +
            "where r.data BETWEEN :firstDay AND :lastDay")
    Optional<BigDecimal> calcularValor (@Param("firstDay") LocalDate firstDay, @Param("lastDay") LocalDate lastDay);

    @Query (value = "select new  com.example.orcamentofamiliar.Controllers.Dtos.CategoriasDto(d.categorias,d.valor) from Despesas d " +
            "where d.data  between :firstDay AND :lastDay")
    List<CategoriasDto> acharValorCategorias (@Param("firstDay") LocalDate firstDay, @Param("lastDay") LocalDate lastDay);

}

