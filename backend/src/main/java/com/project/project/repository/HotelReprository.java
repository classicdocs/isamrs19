package com.project.project.repository;

import java.util.List;

import com.project.project.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelReprository extends JpaRepository<Hotel, String> {

    //Hotel save(Hotel hotel);
    List<Hotel> findAll();


}

