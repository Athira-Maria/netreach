package com.netreach.controller;

import com.netreach.dto.ReportRequest;
import com.netreach.model.ConnectivityReport;
import com.netreach.model.ReportStatus;
import com.netreach.service.ConnectivityReportService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ConnectivityReportService service;

    public ReportController(ConnectivityReportService service) {
        this.service = service;
    }

    @PostMapping
    public ConnectivityReport submit(@RequestBody ReportRequest request) {
        return service.submit(request);
    }

    @GetMapping
    public List<ConnectivityReport> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ConnectivityReport getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}/status")
    public ConnectivityReport updateStatus(@PathVariable Long id, @RequestParam ReportStatus value) {
        return service.updateStatus(id, value);
    }    

    @GetMapping("/status/{value}")
    public List<ConnectivityReport> getByStatus(@PathVariable ReportStatus value) {
        return service.getByStatus(value);
    }
}