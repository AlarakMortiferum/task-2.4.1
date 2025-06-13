package ru.netology.testmode.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthInfo {
    private String login;
    private String password;
}
