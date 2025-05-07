package com.capstone.startmap.domain.result.location_search.repository;

import com.capstone.startmap.domain.result.franchise_search.Franchise_search;
import com.capstone.startmap.domain.result.location_search.Location_search;
import com.capstone.startmap.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.Location;
import java.util.List;

@Repository
public interface LocationSearchRepository extends JpaRepository<Location_search, Long> {
    List<Location_search> findAllByUserAndFranchiseResultFalse(User user);
}
