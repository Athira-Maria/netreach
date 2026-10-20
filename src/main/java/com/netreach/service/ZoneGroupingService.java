package com.netreach.service;

import com.netreach.model.AffectedZone;
import com.netreach.model.ConnectivityReport;
import java.util.List;

public interface ZoneGroupingService {
    AffectedZone assignToZone(ConnectivityReport report);
    List<AffectedZone> getAllZones();
    AffectedZone getZone(Long id);
    List<AffectedZone> getSeriousZones();
}