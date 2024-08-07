package com.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.votingsystem.entity.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Integer>{
	@Query(name = "Voter.findByUsername")
    Optional<Voter> findByUsername(@Param("username") String username);
	
}
