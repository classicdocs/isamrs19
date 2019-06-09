package com.project.project.repository;

import com.project.project.model.Hotel_Model.RoomTaken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTakenRepository extends JpaRepository<RoomTaken, Long> {
}
