package com.example.responsereadyapp.controller;

import com.example.responsereadyapp.dto.AedDevicesReadinessCheckStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/AedReadinesscheck")
public class AedReadinesscheckController {

    @GetMapping
    public ResponseEntity<List<AedDevicesReadinessCheckStatusResponse>> getDeviceStatuses() {
        List<AedDevicesReadinessCheckStatusResponse> statuses = new ArrayList<>();
        statuses.add(new AedDevicesReadinessCheckStatusResponse("checkOkAedReady", "Check OK AED ready"));
        statuses.add(new AedDevicesReadinessCheckStatusResponse("readinessIndicatorNegative", "Readiness Indicator Negative"));
        statuses.add(new AedDevicesReadinessCheckStatusResponse("missingSupplies", "Missing supplies"));
        statuses.add(new AedDevicesReadinessCheckStatusResponse("aedIsChirping", "AED is chirping"));
        statuses.add(new AedDevicesReadinessCheckStatusResponse("aedIsDamaged", "AED is Damaged"));
        statuses.add(new AedDevicesReadinessCheckStatusResponse("expiredSupplies", "Expired Supplies"));
        statuses.add(new AedDevicesReadinessCheckStatusResponse("reportEventAedUse", "Report Event/AED Use"));
        statuses.add(new AedDevicesReadinessCheckStatusResponse("other", "Other"));

        return ResponseEntity.ok(statuses);
    }
}
