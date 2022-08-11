package com.example.orcamentofamiliar.Repository;

import com.example.orcamentofamiliar.Entidades.Despesas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DespesasRepository  extends JpaRepository<Despesas,Long> {
   Page<Despesas> findByDescricao(String descricao, Pageable pageable);

    Optional<Despesas> findByDescricaoAndDataBetween(String descricao, LocalDate firstDay, LocalDate lastDay);

      @Query("select d from Despesas d where MONTH(d.data) = ?1 and YEAR(d.data) = ?2")
    Page<Despesas> acharPorMesEAno(int month, int year, Pageable pageable);

   /* @Query("SELECT SUM(r.valor) " + "FROM Despesas r "
            + "WHERE r.categorias = :categorias "+ " AND r.data BETWEEN :firstDay AND :lastDay")
    Optional<BigDecimal> calcularValor (int mes, int ano, LocalDate firstDay, LocalDate lastDay);
*/
}

