package com.yuriikoval1997.atm.data;

import com.yuriikoval1997.atm.pojo.Account;

import java.util.Optional;

public interface AtmRepository {

    boolean putMoney(long amount, Account acc);

    Optional<Long> withdraw(long amount, Account acc);

    boolean transferToAnotherAccount(long amount,
                                     Account from,
                                     Account to);
}
