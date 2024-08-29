package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Administrator;
import com.example.SVT_KVT.repository.AdministratorRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> findById(Integer id) {
        return administratorRepository.findById(id);
    }

    @Transactional
    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Transactional
    public void deleteById(Integer id) {
        administratorRepository.deleteById(id);
    }
}

