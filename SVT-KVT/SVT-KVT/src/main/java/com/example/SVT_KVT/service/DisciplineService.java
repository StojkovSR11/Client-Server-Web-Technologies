package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Discipline;
import com.example.SVT_KVT.model.Facility;
import com.example.SVT_KVT.repository.DisciplineRepository;
import com.example.SVT_KVT.repository.FacilityRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private FacilityRepository facilityRepository;
    
    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> findById(Integer id) {
        return disciplineRepository.findById(id);
    }

    @Transactional
    public Discipline save(Discipline discipline) {
        // Fetch the Facility from the database using the facility ID
        Facility facility = facilityRepository.findById(discipline.getFacility().getId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        
        // Set the fetched facility to the discipline
        discipline.setFacility(facility);

        // Save the discipline in the database
        return disciplineRepository.save(discipline);
    }

    @Transactional
    public void deleteById(Integer id) {
        disciplineRepository.deleteById(id);
    }
}

