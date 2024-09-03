package com.example.SVT_KVT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.example.SVT_KVT.model.Facility;
import com.example.SVT_KVT.repository.FacilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public List<Facility> findAll() {
        // Fetch only active facilities
        List<Facility> facilities = facilityRepository.findByActiveTrue();
        // Recalculate total ratings for all fetched facilities
        facilities.forEach(Facility::calculateTotalRating);
        return facilities;
    }

    public Optional<Facility> findById(Integer id) {
        Optional<Facility> facilityOpt = facilityRepository.findById(id);
        // Recalculate total rating for the fetched facility, if present
        facilityOpt.ifPresent(Facility::calculateTotalRating);
        return facilityOpt;
    }

    @Transactional
    public Facility save(Facility facility) {
        // Recalculate total rating before saving
        facility.calculateTotalRating();
        return facilityRepository.save(facility);
    }

    @Transactional
    public void deleteById(Integer id) {
        // Fetch the facility by ID
        Optional<Facility> facilityOpt = facilityRepository.findById(id);
        if (facilityOpt.isPresent()) {
            Facility facility = facilityOpt.get();
            // Set active to false instead of deleting
            facility.setActive(false);
            // Recalculate total rating before saving
            facility.calculateTotalRating();
            facilityRepository.save(facility);
        }
    }

    @Transactional
    public void deleteByIdPermanently(Integer id) {
        facilityRepository.deleteById(id);
    }

    @Transactional
    public void activateFacility(Integer id) {
        Optional<Facility> facilityOpt = facilityRepository.findById(id);
        if (facilityOpt.isPresent()) {
            Facility facility = facilityOpt.get();
            facility.setActive(true);
            // Recalculate total rating before saving
            facility.calculateTotalRating();
            facilityRepository.save(facility);
        }
    }
}



