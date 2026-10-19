package com.netreach.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConnectivityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    private String description;
    private double latitude;
    private double longitude;
    private String locationName;

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.OPEN;

    private LocalDateTime reportedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private AffectedZone zone;

    public void markResolved() { this.status = ReportStatus.RESOLVED; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public IssueType getIssueType() { return issueType; }
    public void setIssueType(IssueType issueType) { this.issueType = issueType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public ReportStatus getStatus() { return status; }
    public void setStatus(ReportStatus status) { this.status = status; }
    public LocalDateTime getReportedAt() { return reportedAt; }
    public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }
    public User getReporter() { return reporter; }
    public void setReporter(User reporter) { this.reporter = reporter; }
    public AffectedZone getZone() { return zone; }
    public void setZone(AffectedZone zone) { this.zone = zone; }
}