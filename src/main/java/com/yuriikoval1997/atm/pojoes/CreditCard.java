package com.yuriikoval1997.atm.pojoes;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Data
@RequiredArgsConstructor
public class CreditCard {

    private Long id;

    @CreditCardNumber(message = "Not a valid credit card number!")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV!")
    private String ccCVV;
}
