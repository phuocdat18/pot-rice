package com.cg.location;

import com.cg.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRegionRepository extends JpaRepository<LocationRegion, Long> {
}