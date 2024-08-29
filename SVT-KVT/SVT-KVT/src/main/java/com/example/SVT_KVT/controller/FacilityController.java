package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Facility;
import com.example.SVT_KVT.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> facilities = facilityService.findAll();
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Integer id) {
        Optional<Facility> facility = facilityService.findById(id);
        return facility.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Facility> createFacility(@RequestBody Facility facility) {
        Facility savedFacility = facilityService.save(facility);
        return new ResponseEntity<>(savedFacility, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Integer id,
                                                   @RequestBody Facility facility) {
        if (!facilityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        facility.setId(id); // Assuming there's a setId method
        Facility updatedFacility = facilityService.save(facility);
        return new ResponseEntity<>(updatedFacility, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Integer id) {
        if (!facilityService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        facilityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
