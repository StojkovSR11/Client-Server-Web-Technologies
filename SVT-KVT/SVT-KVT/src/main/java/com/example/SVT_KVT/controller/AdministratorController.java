package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Administrator;
import com.example.SVT_KVT.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        List<Administrator> administrators = administratorService.findAll();
        return new ResponseEntity<>(administrators, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable Integer id) {
        Optional<Administrator> administrator = administratorService.findById(id);
        return administrator.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        Administrator savedAdministrator = administratorService.save(administrator);
        return new ResponseEntity<>(savedAdministrator, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable Integer id,
                                                             @RequestBody Administrator administrator) {
        if (!administratorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        administrator.setId(id); // Assuming there's a setId method
        Administrator updatedAdministrator = administratorService.save(administrator);
        return new ResponseEntity<>(updatedAdministrator, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Integer id) {
        if (!administratorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        administratorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

