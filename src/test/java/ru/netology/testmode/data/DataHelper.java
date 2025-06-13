package ru.netology.testmode.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {}

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "password123");
    }

    public static String getVerificationCode() {
        return "12345";
    }

    public static String getFirstCardNumber() {
        return "5559 0000 0000 0001";
    }

    public static String getSecondCardNumber() {
        return "5559 0000 0000 0002";
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}
