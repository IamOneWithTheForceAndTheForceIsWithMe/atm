package com.yuriikoval1997.atm.repositories;

import com.yuriikoval1997.atm.pojoes.CreditCard;
import com.yuriikoval1997.atm.pojoes.User;

import java.util.Optional;

public interface UserRepo {

    User findUserByUsername(String username);

    void register(User user);

    Long addCreditCard(User account, CreditCard creditCard);
}
