package com.project.project.repository;

import com.project.project.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Destination findOneByName(String name);
}
