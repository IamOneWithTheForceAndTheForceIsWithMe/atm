package com.yuriikoval1997.atm.pojoes;

import lombok.Data;

@Data
public class Account {
    private long id;
    private String userName;
    private String password;
    private long creditCardId;

    public Account(String username, String credit_card_number, long credits) {
    }
}
