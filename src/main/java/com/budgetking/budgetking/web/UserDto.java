package com.budgetking.budgetking.web;

import com.budgetking.budgetking.model.PayPeriod;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Remove Lombok Getter/Setter from the dependency exclusion in the plugin tag for spring starter
//      It doesn't seem to matter..? I can run with getters from the Lombok generation that and it doesn't cause exits
@Getter
@Setter
public class UserDto {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private String name;
    private String email;
    private String password;
    private float currentBalance;
    private PayPeriod payPeriod;
    private float payAmount;

    public UserDto(String name, String email, String password, float currentBalance, PayPeriod payPeriod, float payAmount) {
        logger.info("userDto name: {}, email: {}, password {}, currentBalance: {}, payPeriod {}, payAmount: {}", name, email, password, currentBalance, payPeriod, payAmount);
        this.name = name;
        this.email = email;
        this.password = password;
        this.currentBalance = currentBalance;
        this.payPeriod = payPeriod;
        this.payAmount = payAmount;
    }
}

