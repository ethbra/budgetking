package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    enum PayPeriod {Weekly, Biweekly, Monthly}

    private int id;
    private String name;
    private String email;
    private String password;
    private float currentBalance;
    private PayPeriod payPeriod;
    private float payAmount;

}

