package com.netreach.service;

import com.netreach.exception.ResourceNotFoundException;
import com.netreach.model.WifiAccessPoint;
import com.netreach.repository.WifiRepository;
import com.netreach.util.GeoUtils;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WifiAccessPointService {

    private final WifiRepository wifiRepo;

    public WifiAccessPointService(WifiRepository wifiRepo) {
        this.wifiRepo = wifiRepo;
    }

    public WifiAccessPoint register(WifiAccessPoint ap) {
        return wifiRepo.save(ap);
    }

    public List<WifiAccessPoint> getAll() {
        return wifiRepo.findAll();
    }

    public List<WifiAccessPoint> findNearby(double lat, double lng, double radiusKm) {
        return wifiRepo.findByAvailableTrue().stream()
                .filter(ap -> GeoUtils.distanceKm(lat, lng, ap.getLatitude(), ap.getLongitude()) <= radiusKm)
                .sorted(Comparator.comparingDouble(
                        ap -> GeoUtils.distanceKm(lat, lng, ap.getLatitude(), ap.getLongitude())))
                .toList();
    }

    public WifiAccessPoint toggleAvailability(Long id) {
        WifiAccessPoint ap = wifiRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access point not found: " + id));
        ap.toggleAvailability();
        return wifiRepo.save(ap);
    }

    public WifiAccessPoint getById(Long id) {
        return wifiRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access point not found: " + id));
    }
}