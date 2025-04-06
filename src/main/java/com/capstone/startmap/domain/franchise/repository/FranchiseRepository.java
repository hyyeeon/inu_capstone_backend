package com.capstone.startmap.domain.franchise.repository;

import com.capstone.startmap.domain.franchise.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
