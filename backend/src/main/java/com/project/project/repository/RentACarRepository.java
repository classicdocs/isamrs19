package com.project.project.repository;

import com.project.project.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentACarRepository  extends JpaRepository<RentACar, String> {

    RentACar save(RentACar rentACar);

    RentACar findOneByName(String name);

    RentACar findOneById(Long id);

    RentACar deleteById(Long Id);

    List<RentACar> findAll();
}
