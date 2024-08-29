package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Manages;
import com.example.SVT_KVT.service.ManagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manages")
public class ManagesController {

    @Autowired
    private ManagesService managesService;

    @GetMapping
    public ResponseEntity<List<Manages>> getAllManages() {
        List<Manages> managesList = managesService.findAll();
        return new ResponseEntity<>(managesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manages> getManagesById(@PathVariable Integer id) {
        Optional<Manages> manages = managesService.findById(id);
        return manages.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Manages> createManages(@RequestBody Manages manages) {
        Manages savedManages = managesService.save(manages);
        return new ResponseEntity<>(savedManages, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manages> updateManages(@PathVariable Integer id,
                                                 @RequestBody Manages manages) {
        if (!managesService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        manages.setId(id); // Assuming there's a setId method
        Manages updatedManages = managesService.save(manages);
        return new ResponseEntity<>(updatedManages, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManages(@PathVariable Integer id) {
        if (!managesService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        managesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
