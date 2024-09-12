package com.budgetking.budgetking.web;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    private String email;
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
