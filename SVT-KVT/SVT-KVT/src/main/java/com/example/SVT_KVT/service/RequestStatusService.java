package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.RequestStatus;
import com.example.SVT_KVT.repository.RequestStatusRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStatusService {

    @Autowired
    private RequestStatusRepository requestStatusRepository;

    public List<RequestStatus> findAll() {
        return requestStatusRepository.findAll();
    }

    public Optional<RequestStatus> findById(Integer id) {
        return requestStatusRepository.findById(id);
    }

    @Transactional
    public RequestStatus save(RequestStatus requestStatus) {
        return requestStatusRepository.save(requestStatus);
    }

    @Transactional
    public void deleteById(Integer id) {
        requestStatusRepository.deleteById(id);
    }
}

