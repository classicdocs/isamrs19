package com.project.project.repository;

import com.project.project.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Optional<Destination> findOneByNameAndZip(String name, String zip);

    Set<Destination> findAllByName(String name);
}
