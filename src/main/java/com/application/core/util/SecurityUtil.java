package com.application.core.util;

import java.util.UUID;

public class SecurityUtil {

    private static final String NUMEROS = "0123456789";
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String generateRandomKey(int length) {
        return generateRandom(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    public static String generateCode2FA(int length) {
        return generateRandom(NUMEROS, length);
    }

    public static String generateConfirmationKey() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandom(String key, int length) {
        StringBuilder keySecret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            keySecret.append(key.charAt((int) (Math.random() * key.length())));
        }
        return keySecret.toString();
    }
}
