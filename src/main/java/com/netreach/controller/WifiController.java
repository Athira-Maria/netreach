package com.netreach.controller;

import com.netreach.model.WifiAccessPoint;
import com.netreach.service.WifiAccessPointService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wifi")
public class WifiController {

    private final WifiAccessPointService service;

    public WifiController(WifiAccessPointService service) {
        this.service = service;
    }

    @PostMapping
    public WifiAccessPoint register(@RequestBody WifiAccessPoint ap) {
        return service.register(ap);
    }

    @GetMapping
    public List<WifiAccessPoint> getAll() {
        return service.getAll();
    }

    @GetMapping("/nearby")
    public List<WifiAccessPoint> nearby(@RequestParam double lat, @RequestParam double lng,
                                        @RequestParam(defaultValue = "1.0") double radiusKm) {
        return service.findNearby(lat, lng, radiusKm);
    }

    @PutMapping("/{id}/toggle")
    public WifiAccessPoint toggle(@PathVariable Long id) {
        return service.toggleAvailability(id);
    }
}