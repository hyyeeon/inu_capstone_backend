package com.capstone.startmap.domain.result.repository;

import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByUser(User user);
    Optional<Result> findByResultIdAndUser(Long id, User user);
}
