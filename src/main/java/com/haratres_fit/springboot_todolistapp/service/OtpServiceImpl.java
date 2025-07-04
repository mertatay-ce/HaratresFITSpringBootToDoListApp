package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.user.GenerateOtpDto;
import com.haratres_fit.springboot_todolistapp.dto.user.ResultPasswordResetDto;
import com.haratres_fit.springboot_todolistapp.exception.model.otp.OtpNotCreatedException;
import com.haratres_fit.springboot_todolistapp.exception.model.otp.OtpNotFoundException;
import com.haratres_fit.springboot_todolistapp.exception.model.user.EmailNotFoundException;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;
import com.haratres_fit.springboot_todolistapp.model.entity.security.OtpToken;
import com.haratres_fit.springboot_todolistapp.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    //TODO: generateOtp metodu verilecek ENUM a göre kontrol edilip tek bir metoda dönüştürülsün
    @Override
    public String generateOtp(String email, OtpType otpType) {
        Optional<User> optionalUser = userService.findByEmail(email);
        return optionalUser.map(user -> {
            // User yoksa:
            System.out.println(user);
            return null;
        }).orElseGet(() -> {
            String newOtp = String.valueOf(100000 + new Random().nextInt(900000)); //TODO : Ayrı method 43-58 satırları
            OtpToken otpEntity = new OtpToken();
            otpEntity.setUser(optionalUser.get());
            otpEntity.setToken(newOtp);
            otpEntity.setOtpType(otpType);
            LocalDateTime dateTime = Instant.ofEpochMilli(System.currentTimeMillis() + 300000)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            otpEntity.setExpiryDateTime(dateTime);
            otpRepository.save(otpEntity);

            sendOtp(email, newOtp, otpEntity.getOtpType());
            return newOtp;
        }).toString();

    }


    @Override
    public void sendOtp(String email, String otp, OtpType otp_type) {

        SimpleMailMessage message = new SimpleMailMessage();

        if (otp_type.equals(OtpType.REGISTER_OTP_TYPE)){
            message.setSubject("Register OTP Code");
            message.setText("Sent OTP code into your email for register:" +otp + "\n This otp code expires in 5 minutes. Please use in this time.");
            message.setTo(email);
            emailService.sendEmail(message);
        }else if (otp_type.equals(OtpType.RESET_PASSWORD_OTP_TYPE)){
            message.setSubject("Reset Password OTP Code");
            message.setText("Sent OTP code into your email for reset password:" +otp + "\n This otp code expires in 5 minutes. Please use in this time.");
            message.setTo(email);
            emailService.sendEmail(message);
        }else{
            throw new OtpNotFoundException("Otp is not found!");
        }
    }


    //TODO: validateOtp metodu verilecek ENUM a göre kontrol edilip tek bir metoda dönüştürülsün


    @Override
    public boolean validateOtp(String email,String otp, OtpType otpType) {
        OtpToken otpEntity = findOTPCodeFromUserByEmail(email);
        if (otpType.equals(otpEntity.getOtpType())){
            LocalDateTime expiryDateTime = otpEntity.getExpiryDateTime();

            long expiryMillis = expiryDateTime
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli();

            return otpEntity.getToken().equals(otp) && System.currentTimeMillis() <= expiryMillis;
        }else{
            throw new OtpNotFoundException("Otp is not found!");
        }

    }

    //TODO: resetPassword -> UserService


    @Override
    public ResultPasswordResetDto deleteOtp(GenerateOtpDto generateOtpDto) {
        User user = userService.findByEmail(generateOtpDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email kayıtlı değil"));

        OtpToken otp = user.getOtpToken();

        if (!validateOtp(generateOtpDto.getEmail(),otp.getToken(),generateOtpDto.getOtpType())){
            //User ile otpnin ilişkisini kaldır
            user.setOtpToken(null);
            //User'ı güncelle
            userService.saveUser(user);
            //OTP remove et
            otpRepository.delete(otp);
            return new ResultPasswordResetDto("OTP başarıyla kaldırıldı!","");
        }else{
            return new ResultPasswordResetDto("","OTP kaldırma işlemi başarısız!");
        }

    }

    @Override
    public OtpToken findOTPCodeFromUserByEmail(String email) {
        if (email == null){
            throw new EmailNotFoundException("Did not found email address!");
        }else{
            return otpRepository.findOTPCodeFromUserByEmail(email);
        }

    }
}
