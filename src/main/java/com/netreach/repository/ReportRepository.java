package com.netreach.repository;

import com.netreach.model.ConnectivityReport;
import com.netreach.model.ReportStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ConnectivityReport, Long> {
    List<ConnectivityReport> findByStatus(ReportStatus status);
}