package com.capstone.startmap.domain.shop.repository;

import com.capstone.startmap.domain.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
