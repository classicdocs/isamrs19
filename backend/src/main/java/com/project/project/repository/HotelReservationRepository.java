package com.project.project.repository;

import com.project.project.model.Hotel_Model.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelReservationRepository  extends JpaRepository<HotelReservation, Long> {

    HotelReservation findOneById(Long id);

}
