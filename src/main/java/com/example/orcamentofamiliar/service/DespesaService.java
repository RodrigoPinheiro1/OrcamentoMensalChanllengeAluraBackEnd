package com.example.orcamentofamiliar.service;

import com.example.orcamentofamiliar.Controllers.Dtos.Despesas.DespesasDto;
import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesasRepository despesasRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DespesasDto cadastro(DespesasDto despesasDto) {
        Despesas despesas = modelMapper.map(despesasDto, Despesas.class);
        despesasRepository.save(despesas);

        return modelMapper.map(despesas, DespesasDto.class);
    }

    public DespesasDto update(DespesasDto despesasDto, Long id) {
        Optional<Despesas> despesas = despesasRepository.findById(id);
        if (!despesas.isPresent()) {
            throw new EntityNotFoundException();
        }

        Despesas update = despesasRepository.getReferenceById(id);
        update.setDescricao(despesasDto.getDescricao());
        update.setValor(despesasDto.getValor());
        update.setCategorias(despesasDto.getCategorias());

        despesasRepository.save(update);
        return modelMapper.map(update, DespesasDto.class);

    }

    public Page<DespesasDto> buscarPelaData(int mes, int ano, Pageable pageable) {
        return despesasRepository.acharPorMesEAno(mes, ano, pageable)
                .map(despesas -> modelMapper.map(despesas, DespesasDto.class));
    }

    public Page<DespesasDto> buscarTudoOuPorNome(String descricao, Pageable pageable) {

        if (descricao == null) {
            return despesasRepository.findAll(pageable)
                    .map(despesas -> modelMapper.map(despesas, DespesasDto.class));
        }
        return despesasRepository.findByDescricao(descricao, pageable)
                .map(despesas -> modelMapper.map(despesas, DespesasDto.class));
    }

    public DespesasDto acharPorId(Long id) {
        Optional<Despesas> despesas = despesasRepository.findById(id);
        return modelMapper.map(despesas, DespesasDto.class);
    }

    public Boolean isFound(Long id){
        return despesasRepository.findById(id).isPresent();
    }

    public void deletarPorId(Long id) {
       despesasRepository.deleteById(id);
    }
    public Boolean InsertIsRepeat(DespesasDto despesasDto) {
        LocalDate firstDay = despesasDto.getData().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = despesasDto.getData().with(TemporalAdjusters.lastDayOfMonth());

        return despesasRepository.findByDescricaoAndDataBetween(despesasDto.getDescricao(), firstDay, lastDay).isPresent();
    }


    public Boolean UpdateIsRepeated(Long id, DespesasDto despesasDto) {

        LocalDate firstDay = despesasDto.getData().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = despesasDto.getData().with(TemporalAdjusters.lastDayOfMonth());

        return despesasRepository.findByIdNotAndDescricaoAndDataBetween(id, despesasDto.getDescricao(), firstDay, lastDay).isPresent();
    }


}
