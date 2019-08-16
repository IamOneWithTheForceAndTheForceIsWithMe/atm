package com.yuriikoval1997.atm.repositories;

import com.yuriikoval1997.atm.pojoes.CreditCard;

import java.util.Collection;

public interface CreditCardRepo {

    enum Status{
        SUCCESS, NOT_ENOUGH_CREDITS, NO_SUCH_CREDIT_CARD
    }

    Collection<CreditCard> findCreditCards(Long id);

    CreditCard findCreditCardByNumber(String ccNumber);

    Status put(String ccNumber, long amount);

    Status withdraw(String ccNumber, long amount);

    Status transfer(String from, String to, long amount);
}
