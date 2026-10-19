package com.netreach.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    private String department;

    @Override
    public String getRoleName() { return "ADMIN"; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}