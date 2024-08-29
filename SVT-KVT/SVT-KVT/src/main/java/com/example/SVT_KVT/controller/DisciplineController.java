package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Discipline;
import com.example.SVT_KVT.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public ResponseEntity<List<Discipline>> getAllDisciplines() {
        List<Discipline> disciplines = disciplineService.findAll();
        return new ResponseEntity<>(disciplines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Integer id) {
        Optional<Discipline> discipline = disciplineService.findById(id);
        return discipline.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline) {
        Discipline savedDiscipline = disciplineService.save(discipline);
        return new ResponseEntity<>(savedDiscipline, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Integer id,
                                                       @RequestBody Discipline discipline) {
        if (!disciplineService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        discipline.setId(id); // Assuming there's a setId method
        Discipline updatedDiscipline = disciplineService.save(discipline);
        return new ResponseEntity<>(updatedDiscipline, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Integer id) {
        if (!disciplineService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        disciplineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

