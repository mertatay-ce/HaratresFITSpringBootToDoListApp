package com.haratres_fit.springboot_todolistapp.repository;

import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM roles WHERE role_name = :name")
    Optional<Role> findByName(@Param("name") String role_name);
}
