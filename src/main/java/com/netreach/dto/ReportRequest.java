package com.netreach.dto;

import com.netreach.model.IssueType;

public class ReportRequest {

    private Long userId;
    private IssueType issueType;
    private String description;
    private double latitude;
    private double longitude;
    private String locationName;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
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
}