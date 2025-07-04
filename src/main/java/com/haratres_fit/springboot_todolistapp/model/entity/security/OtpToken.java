package com.haratres_fit.springboot_todolistapp.model.entity.security;

import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
//TODO: OtpType olarak ENUM tanımlama (register, resetPassword)
@Entity
@Table(name = "otp_codes")//TODO: table_name adı değişecek
public class OtpToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiry_date_time")
    private LocalDateTime expiryDateTime;


    @Enumerated(EnumType.STRING)
    @Column(name = "otp_type")
    private OtpType otpType;

    public OtpToken(Long id, String token, User user, LocalDateTime expiryDateTime, OtpType otpType) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiryDateTime = expiryDateTime;
        this.otpType = otpType;
    }

    public OtpToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public OtpToken() {
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OtpType getOtpType() {
        return otpType;
    }

    public void setOtpType(OtpType otpType) {
        this.otpType = otpType;
    }
    @Override
    public String toString() {
        return "OtpToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", expiryDateTime=" + expiryDateTime +
                ", otpType=" + otpType +
                '}';
    }
}
