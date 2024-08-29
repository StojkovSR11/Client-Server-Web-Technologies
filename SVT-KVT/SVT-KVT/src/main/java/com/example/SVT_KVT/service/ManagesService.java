package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Manages;
import com.example.SVT_KVT.repository.ManagesRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ManagesService {

    @Autowired
    private ManagesRepository managesRepository;

    public List<Manages> findAll() {
        return managesRepository.findAll();
    }

    public Optional<Manages> findById(Integer id) {
        return managesRepository.findById(id);
    }

    @Transactional
    public Manages save(Manages manages) {
        return managesRepository.save(manages);
    }

    @Transactional
    public void deleteById(Integer id) {
        managesRepository.deleteById(id);
    }
}


