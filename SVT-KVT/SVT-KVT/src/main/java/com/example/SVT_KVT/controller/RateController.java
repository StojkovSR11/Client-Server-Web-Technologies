package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Rate;
import com.example.SVT_KVT.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rates")
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates() {
        List<Rate> rates = rateService.findAll();
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rate> getRateById(@PathVariable Integer id) {
        Optional<Rate> rate = rateService.findById(id);
        return rate.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rate> createRate(@RequestBody Rate rate) {
        Rate savedRate = rateService.save(rate);
        return new ResponseEntity<>(savedRate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rate> updateRate(@PathVariable Integer id,
                                           @RequestBody Rate rate) {
        if (!rateService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rate.setId(id); // Assuming there's a setId method
        Rate updatedRate = rateService.save(rate);
        return new ResponseEntity<>(updatedRate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Integer id) {
        if (!rateService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        rateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
