package com.capstone.startmap.domain.result.repository;

import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByUser(User user);
}
