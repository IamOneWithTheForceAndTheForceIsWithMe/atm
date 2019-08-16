package com.yuriikoval1997.atm.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface CreditCardService {
    List<Integer> BANKNOTES = Collections.unmodifiableList(Arrays.asList(100, 200, 500));

    boolean isAppropriateAmount(long amount);
}
