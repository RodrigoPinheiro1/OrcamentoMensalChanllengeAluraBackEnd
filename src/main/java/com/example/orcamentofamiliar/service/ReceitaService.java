package com.example.orcamentofamiliar.service;

import com.example.orcamentofamiliar.Controllers.Dtos.ReceitasDto;
import com.example.orcamentofamiliar.Entidades.Receitas;
import com.example.orcamentofamiliar.Repository.ReceitasRepository;
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
public class ReceitaService {

    @Autowired
    private ReceitasRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ReceitasDto cadastro(ReceitasDto dto) {
        Receitas receitas = modelMapper.map(dto, Receitas.class);
        repository.save(receitas);

        return modelMapper.map(receitas, ReceitasDto.class);
    }

    public ReceitasDto update(ReceitasDto dto, Long id) {
        Optional<Receitas> receitas = repository.findById(id);
        if (!receitas.isPresent()) {
            throw new EntityNotFoundException();
        }

        Receitas update = repository.getReferenceById(id);
        update.setDescricao(dto.getDescricao());
        update.setValor(dto.getValor());

        repository.save(update);
        return modelMapper.map(update, ReceitasDto.class);

    }

    public Page<ReceitasDto> buscarPelaData(int mes, int ano, Pageable pageable) {
        return repository.acharDataEMes(mes, ano, pageable)
                .map(receitas -> modelMapper.map(receitas, ReceitasDto.class));
    }

    public Page<ReceitasDto> buscarTudoOuPorNome(String descricao, Pageable pageable) {

        if (descricao == null) {
            return repository.findAll(pageable)
                    .map(receitas -> modelMapper.map(receitas, ReceitasDto.class));
        }
        return repository.findByDescricao(descricao, pageable)
                .map(receitas -> modelMapper.map(receitas, ReceitasDto.class));
    }

    public ReceitasDto acharPorId(Long id) {
        Optional<Receitas> receitas = repository.findById(id);
        return modelMapper.map(receitas, ReceitasDto.class);
    }

    public Boolean isFound(Long id){
        return repository.findById(id).isPresent();
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }
    public Boolean InsertIsRepeat(ReceitasDto dto) {
        LocalDate firstDay = dto.getData().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = dto.getData().with(TemporalAdjusters.lastDayOfMonth());

        return repository.findByDescricaoAndDataBetween(dto.getDescricao(), firstDay, lastDay).isPresent();
    }


    public Boolean UpdateIsRepeated(Long id, ReceitasDto dto) {

        LocalDate firstDay = dto.getData().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = dto.getData().with(TemporalAdjusters.lastDayOfMonth());

        return repository.findByIdNotAndDescricaoAndDataBetween(id, dto.getDescricao(), firstDay, lastDay).isPresent();
    }



}
