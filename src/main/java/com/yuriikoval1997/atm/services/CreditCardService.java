package com.yuriikoval1997.atm.services;

public interface CreditCardService {
    int ONE_HUNDRED = 100;
    int TWO_HUNDRED = 200;
    int FIVE_HUNDRED = 500;

    boolean isAppropriateAmount(long amount);
}
