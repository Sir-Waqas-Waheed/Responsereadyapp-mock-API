package com.example.responsereadyapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DashboardResponse {

    private Stats stats;
    private List<CheckDue> checkDue;
    private List<SentSupplies> sentSupplies;

    @Setter
    @Getter
    public static class Stats {
        private int totalSites;
        private int healthScore;
        private int totalAeds;
        private int totalFirstAids;
        private int totalPendingCheck;
        private int totalSentSupplies;

    }

    @Setter
    @Getter
    public static class CheckDue {
        private String image;
        private int serialNumber;
        private String status;
        private String deviceName;
        private String cabinet;

    }

    @Setter
    @Getter
    public static class SentSupplies {
        private int serialNumber;
        private String status;
        private String deviceName;
        private String cabinet;

    }
}
