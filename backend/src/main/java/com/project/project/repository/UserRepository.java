package com.project.project.repository;

import com.project.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByUsernameAndIdNot(String username, Long id);

    Optional<User> findOneById(Long id);

    @Query(value = "select * from users u where u.id != ?1 and u.dtype like 'RegisteredUser' and (u.firstname like ?2 or u.lastname like ?2 or u.username like ?2)", nativeQuery = true)
    Set<User> searchFriends(Long id, String name);


}
