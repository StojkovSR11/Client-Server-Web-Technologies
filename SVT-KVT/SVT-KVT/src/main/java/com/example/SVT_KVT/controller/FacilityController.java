package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Discipline;
import com.example.SVT_KVT.model.Facility;
import com.example.SVT_KVT.model.WorkDay;
import com.example.SVT_KVT.service.DisciplineService;
import com.example.SVT_KVT.service.FacilityService;
import com.example.SVT_KVT.service.WorkDayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;
    
    @Autowired
    private WorkDayService workDayService;  
    
    @Autowired
    private DisciplineService disciplineService;

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


        // Set facility reference in workDays and disciplines
        for (WorkDay workDay : facility.getWorkDays()) {
            workDay.setFacility(facility);
            workDay.setValidFrom(LocalDate.now()); 

        }
        for (Discipline discipline : facility.getDisciplines()) {
        	discipline.setFacility(facility);
        }

        // Save the facility last, after all associations are set
        Facility savedFacility = facilityService.save(facility);
        
        // Log the saved facility
        System.out.println("Saved Facility: " + savedFacility);

        return new ResponseEntity<>(savedFacility, HttpStatus.CREATED);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Integer id,
                                                   @RequestBody Facility facility) {
    	
        // Set facility reference in workDays and disciplines
        for (WorkDay workDay : facility.getWorkDays()) {
            workDay.setFacility(facility);
            workDay.setValidFrom(LocalDate.now()); 

        }
        for (Discipline discipline : facility.getDisciplines()) {
        	discipline.setFacility(facility);
        }
        
        return facilityService.findById(id)
                .map(existingFacility -> {
                    facility.setId(id); // Ensure the ID from the path is used
                    Facility updatedFacility = facilityService.save(facility);
                    return new ResponseEntity<>(updatedFacility, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
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
