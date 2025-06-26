package com.haratres_fit.springboot_todolistapp.dto;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDtoImpl implements RoleDto{
    private EntityManager entityManager;

    @Autowired
    public RoleDtoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
