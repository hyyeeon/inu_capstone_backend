package com.capstone.startmap.domain.location_result.repository;

import com.capstone.startmap.domain.location_result.Location_result;
import com.capstone.startmap.domain.result.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationResultRepository extends JpaRepository<Location_result, Long> {
    List<Location_result> findAllByResult(Result result);
}
