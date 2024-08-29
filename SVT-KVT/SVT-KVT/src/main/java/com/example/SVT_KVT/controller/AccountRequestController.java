package com.example.SVT_KVT.controller;


import com.example.SVT_KVT.model.AccountRequest;
import com.example.SVT_KVT.service.AccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account-requests")
public class AccountRequestController {

    @Autowired
    private AccountRequestService accountRequestService;

    @GetMapping
    public ResponseEntity<List<AccountRequest>> getAllAccountRequests() {
        List<AccountRequest> accountRequests = accountRequestService.findAll();
        return new ResponseEntity<>(accountRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRequest> getAccountRequestById(@PathVariable Integer id) {
        Optional<AccountRequest> accountRequest = accountRequestService.findById(id);
        return accountRequest.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccountRequest> createAccountRequest(@RequestBody AccountRequest accountRequest) {
        AccountRequest savedAccountRequest = accountRequestService.save(accountRequest);
        return new ResponseEntity<>(savedAccountRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountRequest> updateAccountRequest(@PathVariable Integer id,
                                                                @RequestBody AccountRequest accountRequest) {
        if (!accountRequestService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        accountRequest.setId(id);
        AccountRequest updatedAccountRequest = accountRequestService.save(accountRequest);
        return new ResponseEntity<>(updatedAccountRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountRequest(@PathVariable Integer id) {
        if (!accountRequestService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        accountRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

