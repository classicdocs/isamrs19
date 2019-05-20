package com.project.project.repository;

import com.project.project.model.FlightInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightInvitationRepository extends JpaRepository<FlightInvitation, Long> {

    @Override
    Optional<FlightInvitation> findById(Long aLong);
}