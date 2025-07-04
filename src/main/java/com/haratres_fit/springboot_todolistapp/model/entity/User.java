package com.haratres_fit.springboot_todolistapp.model.entity;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.user.Gender;
import com.haratres_fit.springboot_todolistapp.model.entity.security.OtpToken;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    @NotNull(message = "User id must have id.")
    private int id;

    @Column(name="first_name",nullable = false,length = 50)
    @NotNull(message = "User first name is required.")
    @Size(min = 1, max=50, message = "First name using min 1 character, max 50 character.")
    private String firstName;

    @Column(name="last_name",nullable = false,length = 50)
    @NotNull(message = "User last name is required.")
    @Size(min = 1,max=50,message = "Last name using min 1 character, max 50 character.")
    private String lastName;

    @Column(name="username",nullable = false,unique = true,length = 50)
    @NotNull(message = "Username is required.")
    @Size(min = 1,max=50,message = "Username min 1 character, max 50 character.")
    private String username;

    @Column(name = "password", nullable = false,length = 68)
    @NotNull(message = "Password is required.")
    @Size(min = 8,max=68,message = "Password using min 8 character, max 68 character.")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender",nullable = true)
    private Gender gender;

    @Column(name = "birth_date",nullable = false)
    @NotNull(message = "User birth date is required.")
    private LocalDate birthDate;

    @Column(name = "email",nullable = false,length = 200)
    @NotNull(message = "Email address is required.")
    @Size(min = 1, max = 200, message = "Email must be between 1 and 200 characters")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Column(name = "telephone_number",nullable = false,length = 20)
    @NotNull(message = "Telephone number is required.")
    @Size(min = 1, max=20, message = "Telephone number using min 1 character, max 20 character.")
    private String telephone_number;

    @Column(name = "is_active",nullable = false)
    private boolean active;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    private OtpToken otpToken;


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    //TODO: cascade typelara bak - null setleme durumu olmadan rol kaldırma durumunda user için Cascade delete işlemine tabi olmamasını engelle
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, Gender gender, LocalDate birthDate, String email, String telephone_number, boolean active, OtpToken otpToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.telephone_number = telephone_number;
        this.active = active;
        this.otpToken = otpToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                ", active=" + active +
                '}';
    }

    public OtpToken getOtpToken() {
        return otpToken;
    }

    public void setOtpToken(OtpToken otpToken) {
        this.otpToken = otpToken;
    }
}
