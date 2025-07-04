package com.haratres_fit.springboot_todolistapp.repository;

import com.haratres_fit.springboot_todolistapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.username = :username ")
    User findByUserName(@Param("username") String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findUserWithRoles(@Param("username") String username);
}
