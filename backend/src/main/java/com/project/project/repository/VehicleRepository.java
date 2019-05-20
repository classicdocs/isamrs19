package com.project.project.repository;

import com.project.project.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findOneByName(String name);

    Vehicle findOneById(Long id);
}

