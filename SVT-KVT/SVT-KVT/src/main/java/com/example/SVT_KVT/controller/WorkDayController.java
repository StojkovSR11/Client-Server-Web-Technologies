package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.WorkDay;
import com.example.SVT_KVT.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workdays")
public class WorkDayController {

    @Autowired
    private WorkDayService workDayService;

    @GetMapping
    public ResponseEntity<List<WorkDay>> getAllWorkDays() {
        List<WorkDay> workDays = workDayService.findAll();
        return new ResponseEntity<>(workDays, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDay> getWorkDayById(@PathVariable("id") Integer id) {
        Optional<WorkDay> workDay = workDayService.findById(id);
        return workDay.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WorkDay> createWorkDay(@RequestBody WorkDay workDay) {
        WorkDay savedWorkDay = workDayService.save(workDay);
        return new ResponseEntity<>(savedWorkDay, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkDay> updateWorkDay(@PathVariable("id") Integer id, @RequestBody WorkDay workDay) {
        if (!workDayService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        workDay.setId(id); // Ensure the ID is set for update
        WorkDay updatedWorkDay = workDayService.save(workDay);
        return ResponseEntity.ok(updatedWorkDay);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkDay(@PathVariable("id") Integer id) {
        if (!workDayService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        workDayService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

