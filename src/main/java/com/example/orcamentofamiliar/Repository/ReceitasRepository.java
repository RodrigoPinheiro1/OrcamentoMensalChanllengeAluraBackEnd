package com.example.orcamentofamiliar.Repository;

import com.example.orcamentofamiliar.Entidades.Receitas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReceitasRepository extends JpaRepository <Receitas,Long> {

    Page<Receitas> findByDescricao (String desc, Pageable pageable);


   Optional <Receitas> findByIdNotAndDescricaoAndDataBetween(Long id, String desc, LocalDate inicioMes, LocalDate fimMes);
    @Query("select r from Receitas r where MONTH(r.data) = ?1 and YEAR(r.data) = ?2")
   Page<Receitas> acharDataEMes(int month, int year, Pageable pageable);

    @Query (value = "select sum(r.valor) from Receitas r " +
            "where r.data BETWEEN :firstDay AND :lastDay")
    Optional<BigDecimal> calcularValor (@Param("firstDay") LocalDate firstDay, @Param("lastDay") LocalDate lastDay);

    Optional<Receitas> findByDescricaoAndDataBetween(String descricao, LocalDate firstDay, LocalDate lastDay);

}
