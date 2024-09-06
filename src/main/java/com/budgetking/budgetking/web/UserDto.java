package com.budgetking.budgetking.web;

import lombok.Getter;
import lombok.Setter;
import com.budgetking.budgetking.model.PayPeriod;
// TODO: Remove Lombok Getter/Setter from the dependency exclusion in the plugin tag for spring starter
@Getter @Setter
public class UserDto {

    private String name;
    private String email;
    private String password;
    private float currentBalance;
    private PayPeriod payPeriod;
    private float payAmount;

    public UserDto(String name, String email, String password, float currentBalance, PayPeriod payPeriod, float payAmount) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.currentBalance = currentBalance;
        this.payPeriod = payPeriod;
        this.payAmount = payAmount;
    }
}

