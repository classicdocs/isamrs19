package com.project.project.repository;

import com.project.project.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findSeatsByPassengerId(Long id);
}
