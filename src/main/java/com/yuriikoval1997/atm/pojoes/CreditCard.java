package com.yuriikoval1997.atm.pojoes;

public class CreditCard {
    private final long id;
    private final String creditCardNumber;

    private CreditCard(){
        this.id = -1;
        this.creditCardNumber = "";
    }

    public CreditCard(long id, String creditCardNumber) {
        this.id = id;
        this.creditCardNumber = creditCardNumber;
    }
}
