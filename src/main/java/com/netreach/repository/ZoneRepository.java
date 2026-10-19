package com.netreach.repository;

import com.netreach.model.AffectedZone;
import com.netreach.model.ZoneStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<AffectedZone, Long> {
    List<AffectedZone> findByStatusNot(ZoneStatus status);
}