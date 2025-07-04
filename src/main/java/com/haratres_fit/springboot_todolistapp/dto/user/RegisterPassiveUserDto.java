package com.haratres_fit.springboot_todolistapp.dto.user;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;
import com.haratres_fit.springboot_todolistapp.model.entity.enums.user.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class RegisterPassiveUserDto {

    @NotNull(message = "User first name is required.")
    @Size(min = 1,max=50,message = "First name using min 1 character, max 50 character.")
    private String firstName;


    @NotNull(message = "User last name is required.")
    @Size(min = 1,max=50,message = "Last name using min 1 character, max 50 character.")
    private String lastName;


    @NotNull(message = "Username is required.")
    @Size(min = 1,max=50,message = "Username min 1 character, max 50 character.")
    private String username;


    @NotNull(message = "Password is required.")
    @Size(min = 8,max=68,message = "Password using min 8 character, max 68 character.")
    private String password;


    private Gender gender;


    @NotNull(message = "User birth date is required.")
    private LocalDate birthDate;


    @NotNull(message = "Email address is required.")
    @Size(min = 1, max = 200, message = "Email must be between 1 and 200 characters")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;


    @NotNull(message = "Telephone number is required.")
    @Size(min = 1,max=20,message = "Telephone number using min 1 character, max 20 character.")
    private String telephone_number;

    private OtpType otpType;

    public RegisterPassiveUserDto() {
    }

    public RegisterPassiveUserDto(String firstName, String lastName, String username, String password, Gender gender, LocalDate birthDate, String email, String telephone_number, OtpType otpType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.telephone_number = telephone_number;

        this.otpType = otpType;
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

    public OtpType getOtpType() {
        return otpType;
    }

    public void setOtpType(OtpType otpType) {
        this.otpType = otpType;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", telephone_number='" + telephone_number + '\'' +
                '}';
    }
}
