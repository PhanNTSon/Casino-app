package io.phanson.casino_backend.utils;

import java.security.SecureRandom;

public class Generator {
    public static String generateVerificationCode() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int len = 6;
        final SecureRandom secureRandom = new SecureRandom();

        StringBuilder str = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            str.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
        }

        return str.toString();
    }

}
