package com.example.responsereadyapp.dto;

public class AedDevicesReadinessCheckStatusResponse {
    private String key;
    private String label;

    public AedDevicesReadinessCheckStatusResponse(String key, String label) {
        this.key = key;
        this.label = label;
    }

    // Getters and Setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
