package com.capstone.startmap.domain.franchise_result.repository;

import com.capstone.startmap.domain.franchise_result.Franchise_result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseResultRepository extends JpaRepository<Franchise_result, Long> {
}
