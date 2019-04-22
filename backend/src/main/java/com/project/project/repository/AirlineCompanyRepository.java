package com.project.project.repository;

import com.project.project.dto.AirlineCompanyDTO;
import com.project.project.model.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Long> {

    Optional<AirlineCompany> findOneByName(String name);

    Optional<AirlineCompany> findOneById(Long id);

    Optional<AirlineCompany> findOneByNameAndIdNot(String name, Long id);


    @Query("SELECT new com.project.project.dto.AirlineCompanyDTO(a) FROM AirlineCompany a WHERE a.name IS NOT NULL")
    Set<AirlineCompanyDTO> findAllAirlines();
}
