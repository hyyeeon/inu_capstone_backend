package com.capstone.startmap.domain.building.repository;

import com.capstone.startmap.domain.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
