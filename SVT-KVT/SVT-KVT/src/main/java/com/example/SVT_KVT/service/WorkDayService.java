package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.WorkDay;
import com.example.SVT_KVT.repository.WorkDayRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WorkDayService {

    @Autowired
    private WorkDayRepository workDayRepository;

    public List<WorkDay> findAll() {
        return workDayRepository.findAll();
    }

    public Optional<WorkDay> findById(Integer id) {
        return workDayRepository.findById(id);
    }

    @Transactional
    public WorkDay save(WorkDay workDay) {
        return workDayRepository.save(workDay);
    }

    @Transactional
    public void deleteById(Integer id) {
        workDayRepository.deleteById(id);
    }
}

