package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.Image;
import com.example.SVT_KVT.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.findAll();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Integer id) {
        Optional<Image> image = imageService.findById(id);
        return image.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        Image savedImage = imageService.save(image);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Integer id,
                                             @RequestBody Image image) {
        if (!imageService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        image.setId(id); // Assuming there's a setId method
        Image updatedImage = imageService.save(image);
        return new ResponseEntity<>(updatedImage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id) {
        if (!imageService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        imageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
