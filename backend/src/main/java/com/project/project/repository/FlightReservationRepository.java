package com.project.project.repository;

import com.project.project.model.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

    FlightReservation findOneById(Long id);

}