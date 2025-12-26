package io.phanson.casino_backend.utils;

public class Validator {
    public static boolean validateEmail(String rawEmailString) {
        return rawEmailString.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
