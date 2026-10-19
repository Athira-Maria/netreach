package com.netreach.service;

import com.netreach.exception.ResourceNotFoundException;
import com.netreach.model.AffectedZone;
import com.netreach.model.ConnectivityReport;
import com.netreach.model.ZoneStatus;
import com.netreach.repository.ZoneRepository;
import com.netreach.util.GeoUtils;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ZoneGroupingServiceImpl implements ZoneGroupingService {

    private final ZoneRepository zoneRepo;

    public ZoneGroupingServiceImpl(ZoneRepository zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    @Override
    public AffectedZone assignToZone(ConnectivityReport report) {
        for (AffectedZone z : zoneRepo.findByStatusNot(ZoneStatus.RESOLVED)) {
            double distMeters = GeoUtils.distanceKm(report.getLatitude(), report.getLongitude(),
                    z.getCenterLat(), z.getCenterLng()) * 1000;
            if (distMeters <= z.getRadiusMeters()) {
                z.addReport();
                return zoneRepo.save(z);
            }
        }
        AffectedZone zone = new AffectedZone();
        zone.setName("Zone near " + report.getLocationName());
        zone.setCenterLat(report.getLatitude());
        zone.setCenterLng(report.getLongitude());
        zone.addReport();
        return zoneRepo.save(zone);
    }

    @Override
    public List<AffectedZone> getAllZones() {
        return zoneRepo.findAll();
    }

    @Override
    public AffectedZone getZone(Long id) {
        return zoneRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found: " + id));
    }
}