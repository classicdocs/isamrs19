package com.project.project.repository;

import com.project.project.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}