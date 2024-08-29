package com.example.SVT_KVT.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SVT_KVT.model.AccountRequest;
import com.example.SVT_KVT.repository.AccountRequestRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRequestService {

    @Autowired
    private AccountRequestRepository accountRequestRepository;

    public List<AccountRequest> findAll() {
        return accountRequestRepository.findAll();
    }

    public Optional<AccountRequest> findById(Integer id) {
        return accountRequestRepository.findById(id);
    }

    @Transactional
    public AccountRequest save(AccountRequest accountRequest) {
        return accountRequestRepository.save(accountRequest);
    }

    @Transactional
    public void deleteById(Integer id) {
        accountRequestRepository.deleteById(id);
    }
}

