package com.yuriikoval1997.atm.pojoes;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserForm {
    @Size(min = 5, message = "User name has to have at least 5 characters!")
    private final String username;

    @Size(min = 8, message = "Password requires at least 8 characters!")
    private final String password;

    @CreditCardNumber(message = "Not a valid credit card number!")
    private String ccNUmber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

}
