package com.project.project.repository;

import com.project.project.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Optional<Destination> findOneByName(String name);
}
