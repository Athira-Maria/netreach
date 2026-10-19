package com.netreach.repository;

import com.netreach.model.WifiAccessPoint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WifiRepository extends JpaRepository<WifiAccessPoint, Long> {
    List<WifiAccessPoint> findByAvailableTrue();
}