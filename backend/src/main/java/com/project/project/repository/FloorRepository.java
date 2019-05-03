package com.project.project.repository;

import com.project.project.model.Hotel_Model.HotelFloor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FloorRepository  extends JpaRepository<HotelFloor, Long> {

    Optional<HotelFloor> findOneById(Long hotelFloorId);

}
