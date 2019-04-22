package com.project.project.repository;

import com.project.project.dto.RentACarDTO;
import com.project.project.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface RentACarRepository extends JpaRepository<RentACar, Long> {
    Optional<RentACar> findOneByName(String name);

    RentACar save(RentACar rentACar);
    RentACar findOneById(Long id);

    @Query("SELECT new com.project.project.dto.RentACarDTO(r) FROM RentACar r WHERE r.name IS NOT NULL")
    Set<RentACarDTO> findAllRentACars();
}
