package com.project.project.repository;

import com.project.project.model.VehicleQuickReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleQuickReservationRepository extends JpaRepository<VehicleQuickReservation, Long> {

    VehicleQuickReservation findOneById(Long id);
}
