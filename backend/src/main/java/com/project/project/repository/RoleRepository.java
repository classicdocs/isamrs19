package com.project.project.repository;

import com.project.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneById(Long id);

}
