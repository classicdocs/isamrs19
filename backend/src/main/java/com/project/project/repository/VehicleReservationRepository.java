package com.project.project.repository;

import com.project.project.dto.VehicleReservationDTO;
import com.project.project.model.VehicleReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleReservationRepository extends JpaRepository<VehicleReservation, Long> {
    @Query(value = "select * from vehicle_reservation vr where vr.user_id = ?1", nativeQuery = true)
    List<VehicleReservation> findReservations(Long id);

    VehicleReservation findOneById(Long id);

    @Query(value = "select * from vehicle_reservation vr where vr.rentacar_id = ?1", nativeQuery = true)
    List<VehicleReservation> findReservationsOfRentACar(Long id);
}
