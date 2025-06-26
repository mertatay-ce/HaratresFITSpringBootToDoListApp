package com.haratres_fit.springboot_todolistapp.dto;

import com.haratres_fit.springboot_todolistapp.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDtoImpl implements UserDto{

    private EntityManager entityManager;

    @Autowired
    public UserDtoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public User findByUserName(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where username=:uName and active=true ",User.class);

        query.setParameter("uName",username);

        User resultUser = null;

        try{
            resultUser = query.getSingleResult();
        }catch(Exception ex){
            resultUser = null;
        }

        return resultUser;
    }
}
