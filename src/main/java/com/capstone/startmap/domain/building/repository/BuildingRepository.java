package com.capstone.startmap.domain.building.repository;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    boolean existsByZone(String zone);
}
