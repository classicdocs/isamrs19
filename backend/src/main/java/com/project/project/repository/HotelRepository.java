package com.project.project.repository;

import java.util.List;

import com.project.project.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {

    Hotel save(Hotel hotel);

    Hotel findOneByName(String name);

    Hotel findOneById(Long id);

    List<Hotel> findAll();


}

