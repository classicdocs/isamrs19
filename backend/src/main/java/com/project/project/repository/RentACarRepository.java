package com.project.project.repository;

import com.project.project.model.RentACar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentACarRepository  extends JpaRepository<RentACar, String> {
}
