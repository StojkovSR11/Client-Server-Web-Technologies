package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Rate;
import com.example.SVT_KVT.repository.RateRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    public Optional<Rate> findById(Integer id) {
        return rateRepository.findById(id);
    }

    @Transactional
    public Rate save(Rate rate) {
        return rateRepository.save(rate);
    }

    @Transactional
    public void deleteById(Integer id) {
        rateRepository.deleteById(id);
    }
}

