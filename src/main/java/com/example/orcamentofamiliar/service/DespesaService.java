package com.example.orcamentofamiliar.service;

import com.example.orcamentofamiliar.Controllers.Dtos.DespesasDto;
import com.example.orcamentofamiliar.Entidades.Despesas;
import com.example.orcamentofamiliar.Repository.DespesasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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

    public DespesasDto cadastro(DespesasDto dto) {

        insertIsRepeat(dto);
        Despesas despesas = modelMapper.map(dto, Despesas.class);
        despesasRepository.save(despesas);

        return modelMapper.map(despesas, DespesasDto.class);
    }

    public DespesasDto update(DespesasDto dto, Long id) {

        isFound(id);
        UpdateIsRepeated(id,dto);
        Despesas update = despesasRepository.getReferenceById(id);
        update.setDescricao(dto.getDescricao());
        update.setValor(dto.getValor());
        update.setCategorias(dto.getCategorias());

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


    public DespesasDto isFound(Long id) {

        Despesas despesas = despesasRepository
                .findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(despesas, DespesasDto.class);
    }

    public void deletarPorId(Long id) {
        isFound(id);
        despesasRepository.deleteById(id);
    }

    public DespesasDto insertIsRepeat(DespesasDto despesasDto) {
        LocalDate firstDay = despesasDto.getData().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = despesasDto.getData().with(TemporalAdjusters.lastDayOfMonth());

        Despesas despesas = despesasRepository.findByDescricaoAndDataBetween(despesasDto.getDescricao(),
                firstDay, lastDay).orElseThrow(EntityExistsException::new);

        return modelMapper.map(despesas,DespesasDto.class);
    }

    public DespesasDto UpdateIsRepeated(Long id, DespesasDto despesasDto) {

        LocalDate firstDay = despesasDto.getData().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = despesasDto.getData().with(TemporalAdjusters.lastDayOfMonth());

        Despesas despesas = despesasRepository.findByIdNotAndDescricaoAndDataBetween(id, despesasDto.getDescricao(),
                firstDay, lastDay).orElseThrow(EntityExistsException::new);

        return modelMapper.map(despesas,DespesasDto.class);
    }


}
