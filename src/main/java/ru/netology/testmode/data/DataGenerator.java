package ru.netology.testmode.data;

public class DataGenerator {

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getVerificationCode() {
        return "12345";
    }

    public static class AuthInfo {
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }
        public String getLogin() { return login; }
        public String getPassword() { return password; }
    }
}
