package com.yuriikoval1997.atm.repositories;

import com.yuriikoval1997.atm.pojoes.Account;
import com.yuriikoval1997.atm.pojoes.UserForm;

import java.util.Optional;

public interface AccountRepo {
    long ONE_HUNDRED = 100;
    long TWO_HUNDREDS = 200;
    long FIVE_HUNDREDS = 500;

    enum Status{
        SUCCESSFUL, NOT_ENOUGH_CREDITS
    }

    void createAccount(UserForm userForm);

    Account login(UserForm userForm);

    Account findUserByUsername(String username);
}
