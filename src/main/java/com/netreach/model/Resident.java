package com.netreach.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RESIDENT")
public class Resident extends User {

    private String hostelOrArea;

    @Override
    public String getRoleName() { return "RESIDENT"; }

    public String getHostelOrArea() { return hostelOrArea; }
    public void setHostelOrArea(String hostelOrArea) { this.hostelOrArea = hostelOrArea; }
}