package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Facility;
import com.example.SVT_KVT.model.Review;
import com.example.SVT_KVT.model.User;
import com.example.SVT_KVT.service.FacilityService;
import com.example.SVT_KVT.service.ReviewService;
import com.example.SVT_KVT.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        Optional<Review> review = reviewService.findById(id);
        return review.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestParam Integer userId, 
                                               @RequestParam Integer facilityId, 
                                               @RequestBody Review review) {
        Optional<User> user = userService.findById(userId);
        Optional<Facility> facility = facilityService.findById(facilityId);

        if (!user.isPresent() || !facility.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        review.setUser(user.get());
        review.setFacility(facility.get());
        Review savedReview = reviewService.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id,
                                               @RequestParam Integer userId, 
                                               @RequestParam Integer facilityId, 
                                               @RequestBody Review review) {
        if (!reviewService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> user = userService.findById(userId);
        Optional<Facility> facility = facilityService.findById(facilityId);

        if (!user.isPresent() || !facility.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        review.setId(id);
        review.setUser(user.get());
        review.setFacility(facility.get());
        Review updatedReview = reviewService.save(review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        if (!reviewService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
