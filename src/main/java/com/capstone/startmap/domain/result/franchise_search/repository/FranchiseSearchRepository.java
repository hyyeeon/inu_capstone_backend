package com.capstone.startmap.domain.result.franchise_search.repository;

import com.capstone.startmap.domain.result.franchise_search.Franchise_search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseSearchRepository extends JpaRepository<Franchise_search, Long> {
    List<Franchise_search> findAllByUser_idAndFranchiseResultTrue(Long userId);

}
