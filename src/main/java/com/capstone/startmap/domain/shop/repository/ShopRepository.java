package com.capstone.startmap.domain.shop.repository;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByBuildingId(Building building);
    List<Shop> findByFranchiseId(Franchise franchise);
}
