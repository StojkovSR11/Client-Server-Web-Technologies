package com.example.SVT_KVT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SVT_KVT.model.Facility;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    List<Facility> findByActiveTrue();
}
