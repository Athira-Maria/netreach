package com.netreach.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AffectedZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double centerLat;
    private double centerLng;
    private double radiusMeters = 500;
    private int reportCount = 0;

    @Enumerated(EnumType.STRING)
    private ZoneStatus status = ZoneStatus.WATCH;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "zone")
    @JsonIgnore
    private List<ConnectivityReport> reports = new ArrayList<>();

    public void addReport() {
        reportCount++;
        if (isSerious()) {
            status = ZoneStatus.CONFIRMED;
        }
    }

    public boolean isSerious() { return reportCount >= 3; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getCenterLat() { return centerLat; }
    public void setCenterLat(double centerLat) { this.centerLat = centerLat; }
    public double getCenterLng() { return centerLng; }
    public void setCenterLng(double centerLng) { this.centerLng = centerLng; }
    public double getRadiusMeters() { return radiusMeters; }
    public void setRadiusMeters(double radiusMeters) { this.radiusMeters = radiusMeters; }
    public int getReportCount() { return reportCount; }
    public void setReportCount(int reportCount) { this.reportCount = reportCount; }
    public ZoneStatus getStatus() { return status; }
    public void setStatus(ZoneStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<ConnectivityReport> getReports() { return reports; }
    public void setReports(List<ConnectivityReport> reports) { this.reports = reports; }
}