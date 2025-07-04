package com.haratres_fit.springboot_todolistapp.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage simpleMailMessage);
}
