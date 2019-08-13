package com.yuriikoval1997.atm.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Account {
    private long id;
    private long credits;

    public Account(long id) {
        this.id = id;
    }
}
