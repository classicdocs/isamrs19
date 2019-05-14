package com.project.project.repository;

import com.project.project.model.FlightInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightInvitationRepository extends JpaRepository<FlightInvitation, Long> {
}