package com.haratres_fit.springboot_todolistapp.dto.userdto;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.Gender;

import java.time.LocalDate;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String email;
    private String telephone_number;


    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, Gender gender, LocalDate birthDate, String email, String telephone_number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.telephone_number = telephone_number;

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


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                '}';
    }
}
