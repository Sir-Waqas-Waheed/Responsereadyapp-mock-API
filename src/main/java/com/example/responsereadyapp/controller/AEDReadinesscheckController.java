package com.example.responsereadyapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/AEDReadinesscheck")
public class AEDReadinesscheckController {

    @GetMapping
    public ResponseEntity<List<String>> getDeviceStatuses() {
        List<String> statuses = Arrays.asList(
                "Check OK AED ready",
                "Readiness Indicator Negative",
                "Missing supplies",
                "AED is chirping",
                "AED is Damaged",
                "Expired Supplies",
                "Report Event/AED Use",
                "Other"
        );
        return ResponseEntity.ok(statuses);
    }
}
