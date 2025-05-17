package com.capstone.startmap.domain.franchise_result.repository;

import com.capstone.startmap.domain.franchise_result.FranchiseResult;
import com.capstone.startmap.domain.result.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseResultRepository extends JpaRepository<FranchiseResult, Long> {
    List<FranchiseResult> findAllByResult(Result result);
}
