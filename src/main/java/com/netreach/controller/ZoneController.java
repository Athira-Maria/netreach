package com.netreach.controller;

import com.netreach.model.AffectedZone;
import com.netreach.model.WifiAccessPoint;
import com.netreach.service.WifiAccessPointService;
import com.netreach.service.ZoneGroupingService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneGroupingService zoneService;
    private final WifiAccessPointService wifiService;

    public ZoneController(ZoneGroupingService zoneService, WifiAccessPointService wifiService) {
        this.zoneService = zoneService;
        this.wifiService = wifiService;
    }

    @GetMapping
    public List<AffectedZone> getAll() {
        return zoneService.getAllZones();
    }

    @GetMapping("/{id}/nearby-wifi")   // Module 2 + Module 3 together
    public List<WifiAccessPoint> nearbyWifi(@PathVariable Long id,
                                            @RequestParam(defaultValue = "1.0") double radiusKm) {
        AffectedZone zone = zoneService.getZone(id);
        return wifiService.findNearby(zone.getCenterLat(), zone.getCenterLng(), radiusKm);
    }

    @GetMapping("/serious")
    public List<AffectedZone> serious() {
        return zoneService.getSeriousZones();
    }
}