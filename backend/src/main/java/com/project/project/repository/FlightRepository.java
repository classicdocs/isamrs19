package com.project.project.repository;

import com.project.project.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAll();

    @Query(value = "select * from flight f where f.start_destination_id in ?1 and f.final_destination_id in ?2 and f.departure_date = ?3", nativeQuery = true)
    Set<Flight> search(Set<Long> startDestination, Set<Long> finalDestination, String departureDate);

}