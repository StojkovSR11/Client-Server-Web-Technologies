package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Exercise;
import com.example.SVT_KVT.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Integer id) {
        Optional<Exercise> exercise = exerciseService.findById(id);
        return exercise.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createExercise(@RequestBody Exercise exercise) {
        // Validate if the date is in the future
        LocalDate currentDate = LocalDate.now();
        if (exercise.getDate().isBefore(currentDate)) {
            return new ResponseEntity<>("The date must be in the future.", HttpStatus.BAD_REQUEST);
        }

        // Validate if fromTime is less than untilTime
        LocalTime fromTime = exercise.getFromTime();
        LocalTime untilTime = exercise.getUntilTime();

        if (!fromTime.isBefore(untilTime)) {
            return new ResponseEntity<>("The fromTime must be earlier than the untilTime.", HttpStatus.BAD_REQUEST);
        }

        // If both validations pass, save the exercise
        Exercise savedExercise = exerciseService.save(exercise);
        return new ResponseEntity<>(savedExercise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Integer id,
                                                   @RequestBody Exercise exercise) {
        if (!exerciseService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        exercise.setId(id); // Assuming there's a setId method
        Exercise updatedExercise = exerciseService.save(exercise);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Integer id) {
        if (!exerciseService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        exerciseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

