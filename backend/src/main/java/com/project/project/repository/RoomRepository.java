package com.project.project.repository;

import com.project.project.model.Hotel_Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findOneById(Long id);

    Room save(Room room);
}