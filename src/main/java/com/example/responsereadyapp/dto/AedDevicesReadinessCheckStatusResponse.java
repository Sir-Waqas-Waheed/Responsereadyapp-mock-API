package com.example.responsereadyapp.dto;

public class AedDevicesReadinessCheckStatusResponse {
    private String value;
    private String label;

    public AedDevicesReadinessCheckStatusResponse(String value, String label) {
        this.value = value;
        this.label = label;
    }

    // Getters and Setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
