package com.project.project.repository;

import java.util.List;
import java.util.Optional;
import com.project.project.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    Hotel save(Hotel hotel);

    Optional<Hotel> findOneByName(String name);

    Hotel findOneById(Long id);

    List<Hotel> findAll();
}

