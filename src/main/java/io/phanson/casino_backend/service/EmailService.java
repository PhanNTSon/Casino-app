package io.phanson.casino_backend.service;

import org.springframework.stereotype.Service;

import io.micrometer.common.lang.NonNull;

@Service
public interface EmailService {

    public void sendEmail(String to, String subject, String content);

    public void sendHtmlEmail(@NonNull String to, @NonNull String subject, @NonNull String htmlContent);
}
