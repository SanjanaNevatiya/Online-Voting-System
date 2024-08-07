package com.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.votingsystem.entity.Candidate;


public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
	@Query(name = "Candidate.findByUsername")
    Optional<Candidate> findByUsername(@Param("username") String username);
}
