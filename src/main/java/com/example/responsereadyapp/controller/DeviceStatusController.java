package com.example.responsereadyapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/AeddeviceStatus")
public class DeviceStatusController {

    @GetMapping
    public ResponseEntity<List<String>> getDeviceStatuses() {
        List<String> statuses = Arrays.asList("Active", "Inactive", "Dormant");
        return ResponseEntity.ok(statuses);
    }
}
