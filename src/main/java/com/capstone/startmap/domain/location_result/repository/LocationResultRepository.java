package com.capstone.startmap.domain.location_result.repository;

import com.capstone.startmap.domain.location_result.Location_result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationResultRepository extends JpaRepository<Location_result, Long> {
}
