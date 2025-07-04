package com.haratres_fit.springboot_todolistapp.repository;

import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.model.entity.security.OtpToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpToken,Long> {
    @Query(value = "SELECT c FROM OtpToken c JOIN FETCH c.user WHERE c.user.email = :email ")
    OtpToken findOTPCodeFromUserByEmail(@Param("email") String email);

    Optional<OtpToken> findByUserAndToken(User user, String token);
    void deleteByUser(User user);
}
