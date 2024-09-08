package com.budgetking.budgetking.web;

import lombok.Getter;
import lombok.Setter;
import com.budgetking.budgetking.model.PayPeriod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Remove Lombok Getter/Setter from the dependency exclusion in the plugin tag for spring starter
//      It doesn't seem to matter..? I can run with getters from the Lombok generation that and it doesn't cause exits
@Getter @Setter
public class UserDto {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private String name;
    private String email;
    private String password;
    private float currentBalance;
    private PayPeriod payPeriod;
    private float payAmount;

    public UserDto(String name, String email, String password, float currentBalance, PayPeriod payPeriod, float payAmount) {
        logger.info("userDto name: {}", name);
        this.name = name;
        logger.info("userDto email: {}", email);
        this.email = email;
        logger.info("userDto password: {}", password);
        this.password = password;
        logger.info("userDto currentBalance: {}", currentBalance);
        this.currentBalance = currentBalance;
        logger.info("userDto payPeriod: {}", payPeriod);
        this.payPeriod = payPeriod;
        logger.info("userDto payAmount: {}", payAmount);
        this.payAmount = payAmount;
    }
}

