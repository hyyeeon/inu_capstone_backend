package com.capstone.startmap.domain.building.repository;

import com.capstone.startmap.domain.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

}
