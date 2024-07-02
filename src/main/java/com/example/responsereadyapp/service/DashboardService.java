package com.example.responsereadyapp.service;

import com.example.responsereadyapp.dto.DashboardResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    public DashboardResponse getDashboardData() {
        DashboardResponse response = new DashboardResponse();

        DashboardResponse.Stats stats = new DashboardResponse.Stats();
        stats.setTotalSites(10);
        stats.setHealthScore(95);
        stats.setTotalAeds(20);
        stats.setTotalFirstAids(15);
        stats.setTotalPendingCheck(5);
        stats.setTotalSentSupplies(7);

        response.setStats(stats);

        List<DashboardResponse.CheckDue> checkDueList = getCheckDues();
        response.setCheckDue(checkDueList);

        List<DashboardResponse.SentSupplies> sentSuppliesList = getSentSupplies();
        response.setSentSupplies(sentSuppliesList);

        return response;
    }

    private static List<DashboardResponse.SentSupplies> getSentSupplies() {
        List<DashboardResponse.SentSupplies> sentSuppliesList = new ArrayList<>();
        DashboardResponse.SentSupplies sentSupplies1 = new DashboardResponse.SentSupplies();
        sentSupplies1.setSerialNumber(54321);
        sentSupplies1.setStatus("Shipped");
        sentSupplies1.setDeviceName("Device A");
        sentSupplies1.setCabinet("Cabinet A");
        sentSuppliesList.add(sentSupplies1);

        DashboardResponse.SentSupplies sentSupplies2 = new DashboardResponse.SentSupplies();
        sentSupplies2.setSerialNumber(98765);
        sentSupplies2.setStatus("Delivered");
        sentSupplies2.setDeviceName("Device B");
        sentSupplies2.setCabinet("Cabinet B");
        sentSuppliesList.add(sentSupplies2);
        return sentSuppliesList;
    }

    private static List<DashboardResponse.CheckDue> getCheckDues() {
        List<DashboardResponse.CheckDue> checkDueList = new ArrayList<>();
        DashboardResponse.CheckDue checkDue1 = new DashboardResponse.CheckDue();
        checkDue1.setImage("Image Link 1");
        checkDue1.setSerialNumber(12345);
        checkDue1.setStatus("Pending");
        checkDue1.setDeviceName("Device 1");
        checkDue1.setCabinet("Cabinet 1");
        checkDueList.add(checkDue1);

        DashboardResponse.CheckDue checkDue2 = new DashboardResponse.CheckDue();
        checkDue2.setImage("Image Link 2");
        checkDue2.setSerialNumber(67890);
        checkDue2.setStatus("Completed");
        checkDue2.setDeviceName("Device 2");
        checkDue2.setCabinet("Cabinet 2");
        checkDueList.add(checkDue2);
        return checkDueList;
    }
}
