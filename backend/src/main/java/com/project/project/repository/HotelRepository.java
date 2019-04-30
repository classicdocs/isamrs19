package com.project.project.repository;

import java.util.Optional;
import java.util.Set;

import com.project.project.dto.HotelDTO;
import com.project.project.model.Hotel_Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    Hotel save(Hotel hotel);

    Optional<Hotel> findOneByName(String name);

    Optional<Hotel> findOneById(Long id);

    //List<Hotel> findAll();
    @Query("SELECT new com.project.project.dto.HotelDTO(h) FROM Hotel h WHERE h.name IS NOT NULL")
    Set<HotelDTO> findAllHotels();
}

