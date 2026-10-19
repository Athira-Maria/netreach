package com.netreach.service;

import com.netreach.dto.ReportRequest;
import com.netreach.exception.InvalidReportException;
import com.netreach.exception.ResourceNotFoundException;
import com.netreach.model.ConnectivityReport;
import com.netreach.model.ReportStatus;
import com.netreach.model.User;
import com.netreach.repository.ReportRepository;
import com.netreach.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConnectivityReportService {

    private final ReportRepository reportRepo;
    private final UserRepository userRepo;
    private final ZoneGroupingService zoneService;   // depends on the INTERFACE

    public ConnectivityReportService(ReportRepository reportRepo, UserRepository userRepo,
                                     ZoneGroupingService zoneService) {
        this.reportRepo = reportRepo;
        this.userRepo = userRepo;
        this.zoneService = zoneService;
    }

    public ConnectivityReport submit(ReportRequest req) {
        if (req.getUserId() == null || req.getIssueType() == null
                || req.getDescription() == null || req.getDescription().isBlank()) {
            throw new InvalidReportException("userId, issueType and description are required");
        }
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + req.getUserId()));

        ConnectivityReport report = new ConnectivityReport();
        report.setReporter(user);
        report.setIssueType(req.getIssueType());
        report.setDescription(req.getDescription());
        report.setLatitude(req.getLatitude());
        report.setLongitude(req.getLongitude());
        report.setLocationName(req.getLocationName());

        report.setZone(zoneService.assignToZone(report));   // Module 1 calls Module 2
        return reportRepo.save(report);
    }

    public List<ConnectivityReport> getAll() {
        return reportRepo.findAll();
    }

    public ConnectivityReport getById(Long id) {
        return reportRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found: " + id));
    }

    public ConnectivityReport updateStatus(Long id, ReportStatus status) {
        ConnectivityReport report = getById(id);
        report.setStatus(status);
        return reportRepo.save(report);
    }
}