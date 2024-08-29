package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Facility;
import com.example.SVT_KVT.repository.FacilityRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> findById(Integer id) {
        return facilityRepository.findById(id);
    }

    @Transactional
    public Facility save(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Transactional
    public void deleteById(Integer id) {
        facilityRepository.deleteById(id);
    }
}

