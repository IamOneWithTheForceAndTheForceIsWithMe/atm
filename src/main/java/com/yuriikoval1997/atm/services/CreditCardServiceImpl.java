package com.yuriikoval1997.atm.services;

import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Override
    public boolean isAppropriateAmount(long amount) {
        return BANKNOTES
                .stream()
                .anyMatch(note -> amount % note == 0);
    }
}
