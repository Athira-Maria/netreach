package com.netreach.repository;

import com.netreach.model.ConnectivityReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ConnectivityReport, Long> {
}