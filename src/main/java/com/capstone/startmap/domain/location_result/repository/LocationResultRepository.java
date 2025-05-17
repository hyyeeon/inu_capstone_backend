package com.capstone.startmap.domain.location_result.repository;

import com.capstone.startmap.domain.location_result.LocationResult;
import com.capstone.startmap.domain.result.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationResultRepository extends JpaRepository<LocationResult, Long> {
    List<LocationResult> findAllByResult(Result result);
}
