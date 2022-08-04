package com.example.orcamentofamiliar.Repository;

import com.example.orcamentofamiliar.Entidades.Receitas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Repository
public interface ReceitasRepository extends JpaRepository <Receitas,Long> {

    Page<Receitas> findByDescricao (String desc, Pageable pageable);


   Optional <Receitas> findByDescricaoAndDataBetween(String desc, LocalDate inicioMes, LocalDate fimMes);



}
