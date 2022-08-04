package com.example.orcamentofamiliar.Repository;

import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Entidades.Receitas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DespesasRepository  extends JpaRepository<Despesas,Long> {
   Page<Despesas> findByDescricao(String descricao, Pageable pageable);

    Optional<Despesas> findByDescricaoAndDataBetween(String descricao, LocalDate firstDay, LocalDate lastDay);
}
