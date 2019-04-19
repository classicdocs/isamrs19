package com.project.project.repository;

import com.project.project.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentACarRepository extends JpaRepository<RentACar, Long> {
    Optional<RentACar> findOneByName(String name);

    RentACar save(RentACar rentACar);
    RentACar findOneById(Long id);
}
