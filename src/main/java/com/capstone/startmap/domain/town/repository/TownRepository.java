package com.capstone.startmap.domain.town.repository;

import com.capstone.startmap.domain.town.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

}
