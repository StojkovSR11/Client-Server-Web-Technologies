package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Exercise;
import com.example.SVT_KVT.repository.ExerciseRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> findById(Integer id) {
        return exerciseRepository.findById(id);
    }

    @Transactional
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Transactional
    public void deleteById(Integer id) {
        exerciseRepository.deleteById(id);
    }
}

