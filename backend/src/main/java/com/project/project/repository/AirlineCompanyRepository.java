package com.project.project.repository;

import com.project.project.model.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Long> {

    Optional<AirlineCompany> findOneByName(String name);

    Optional<AirlineCompany> findOneById(Long id);
}
