package com.example.SVT_KVT.controller;

import com.example.SVT_KVT.model.RequestStatus;
import com.example.SVT_KVT.service.RequestStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/request-statuses")
public class RequestStatusController {

    @Autowired
    private RequestStatusService requestStatusService;

    @GetMapping
    public ResponseEntity<List<RequestStatus>> getAllRequestStatuses() {
        List<RequestStatus> requestStatuses = requestStatusService.findAll();
        return new ResponseEntity<>(requestStatuses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStatus> getRequestStatusById(@PathVariable Integer id) {
        Optional<RequestStatus> requestStatus = requestStatusService.findById(id);
        return requestStatus.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RequestStatus> createRequestStatus(@RequestBody RequestStatus requestStatus) {
        RequestStatus savedRequestStatus = requestStatusService.save(requestStatus);
        return new ResponseEntity<>(savedRequestStatus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestStatus> updateRequestStatus(@PathVariable Integer id,
                                                             @RequestBody RequestStatus requestStatus) {
        if (!requestStatusService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        requestStatus.setId(id); // Assuming there's a setId method
        RequestStatus updatedRequestStatus = requestStatusService.save(requestStatus);
        return new ResponseEntity<>(updatedRequestStatus, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequestStatus(@PathVariable Integer id) {
        if (!requestStatusService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        requestStatusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
