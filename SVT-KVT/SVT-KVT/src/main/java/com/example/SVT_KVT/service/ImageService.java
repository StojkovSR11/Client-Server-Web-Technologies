package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.Image;
import com.example.SVT_KVT.repository.ImageRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Optional<Image> findById(Integer id) {
        return imageRepository.findById(id);
    }

    @Transactional
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Transactional
    public void deleteById(Integer id) {
        imageRepository.deleteById(id);
    }
}

