package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Discipline;
import com.example.SVT_KVT.repository.DisciplineRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> findById(Integer id) {
        return disciplineRepository.findById(id);
    }

    @Transactional
    public Discipline save(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    @Transactional
    public void deleteById(Integer id) {
        disciplineRepository.deleteById(id);
    }
}

