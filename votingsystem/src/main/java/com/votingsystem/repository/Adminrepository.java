package com.votingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.votingsystem.entity.Admin;

public interface Adminrepository extends JpaRepository<Admin, Integer> {
    @Query(name = "Admin.findByUsername")
    Optional<Admin> findByUsername(@Param("username") String username);
}
