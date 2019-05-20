package com.project.project.repository;

import com.project.project.model.Hotel_Model.HotelDestination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelDestinationsRepository extends JpaRepository<HotelDestination, Long>{
    Optional<HotelDestination> findOneByName(String name);

    Optional<HotelDestination> findOneByHotels_id(Long id);
}
