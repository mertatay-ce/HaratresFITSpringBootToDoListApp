package com.haratres_fit.springboot_todolistapp.model.entity;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true, length = 11)
    @NotNull(message = "User id must have id.")
    private int id;

    @Column(name="first_name",nullable = false,length = 50)
    @NotNull(message = "User first name is required.")
    @Min(value = 1, message = "User first name is must be greater than 1 character.")
    @Max(value = 50, message = "User first name is must be lower than 50 character.")
    private String firstName;

    @Column(name="last_name",nullable = false,length = 50)
    @NotNull(message = "User last name is required.")
    @Min(value = 1, message = "User last name is must be greater than 1 character.")
    @Max(value = 50, message = "User last name is must be lower than 50 character.")
    private String lastName;

    @Column(name="username",nullable = false,unique = true,length = 50)
    @NotNull(message = "Username is required.")
    @Min(value = 1, message = "Username is must be greater than 1 character.")
    @Max(value = 50, message = "Username is must be lower than 50 character.")
    private String username;

    @Column(name = "password", nullable = false,length = 68)
    @NotNull(message = "Password is required.")
    @Min(value = 8, message = "Password is must be greater than 8 character.")
    @Max(value = 60, message = "Password is must be lower than 60 character.")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender",nullable = true,length = 20)
    private Gender gender;

    @Column(name = "birth_date",nullable = false)
    @NotNull(message = "User birth date is required.")
    private Date birthDate;

    @Column(name = "email",nullable = false,length = 200)
    @NotNull(message = "Email address is required.")
    @Min(value = 1, message = "Email address is must be greater than 1 character.")
    @Max(value = 200, message = "Email address is must be lower than 200 character.")
    private String email;

    @Column(name = "telephone_number",nullable = false,length = 20)
    @NotNull(message = "Telephone number is required.")
    @Min(value = 1, message = "Telephone number is must be greater than 1 character.")
    @Max(value = 200, message = "Telephone number is must be lower than 20 character.")
    private String telephone_number;

    @Column(name = "is_active",nullable = false)
    private boolean active;


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, Gender gender, Date birthDate, String email, String telephone_number, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.telephone_number = telephone_number;
        this.active = active;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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
    public void addRole(Role theAppRole) {

        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(theAppRole);
        theAppRole.addUser(this);
    }
}
