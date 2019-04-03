package com.project.project.repository;

import com.project.project.model.HotelAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Long> {
    Optional<HotelAdmin> findOneByUsername(String username);
}
