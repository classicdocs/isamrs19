package com.project.project.repository;

import com.project.project.model.Hotel_Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}