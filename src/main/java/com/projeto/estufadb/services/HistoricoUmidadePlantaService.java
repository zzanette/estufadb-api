package com.projeto.estufadb.services;

import com.projeto.estufadb.domain.HistoricoUmidadePlanta;
import com.projeto.estufadb.domain.Planta;
import com.projeto.estufadb.repositories.HistoricoUmidadePlantaRepository;
import com.projeto.estufadb.repositories.PlantaRepository;
import com.projeto.estufadb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoUmidadePlantaService {

    @Autowired
    private HistoricoUmidadePlantaRepository historicoUmidadePlantaRepository;

    @Autowired
    private PlantaRepository plantaRepository;

    public HistoricoUmidadePlanta findById(Long id) {
        Optional<HistoricoUmidadePlanta> historicoUmidadePlanta = historicoUmidadePlantaRepository.findById(id);
        return historicoUmidadePlanta.orElseThrow(() ->
                new ObjectNotFoundException("Não foi possivel encontrar o historico de umidade com ID " + id)
        );
    }

    public HistoricoUmidadePlanta insert(HistoricoUmidadePlanta newHistoricoUmidadePlanta) {
        newHistoricoUmidadePlanta.setId(null);
        return historicoUmidadePlantaRepository.save(newHistoricoUmidadePlanta);
    }

    public HistoricoUmidadePlanta update(HistoricoUmidadePlanta updatedHistoricoUmidadePlanta) {
        findById(updatedHistoricoUmidadePlanta.getId());
        return historicoUmidadePlantaRepository.save(updatedHistoricoUmidadePlanta);
    }

    public void deleteById(Long id) {
        findById(id);
        historicoUmidadePlantaRepository.deleteById(id);
    }

    public Page<HistoricoUmidadePlanta> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return historicoUmidadePlantaRepository.findAll(pageRequest);
    }
}