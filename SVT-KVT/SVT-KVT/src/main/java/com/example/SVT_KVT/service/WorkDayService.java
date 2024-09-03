package com.example.SVT_KVT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.WorkDay;
import com.example.SVT_KVT.model.Facility;
import com.example.SVT_KVT.repository.WorkDayRepository;
import com.example.SVT_KVT.repository.FacilityRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WorkDayService {

    @Autowired
    private WorkDayRepository workDayRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    public List<WorkDay> findAll() {
        return workDayRepository.findAll();
    }

    public Optional<WorkDay> findById(Integer id) {
        return workDayRepository.findById(id);
    }

    @Transactional
    public WorkDay save(WorkDay workDay) {
        // Fetch the Facility from the database using the facility ID
        Facility facility = facilityRepository.findById(workDay.getFacility().getId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        
        // Set the fetched facility to the workDay
        workDay.setFacility(facility);

        // Save the workDay in the database
        return workDayRepository.save(workDay);
    }

    @Transactional
    public void deleteById(Integer id) {
        workDayRepository.deleteById(id);
    }
}


