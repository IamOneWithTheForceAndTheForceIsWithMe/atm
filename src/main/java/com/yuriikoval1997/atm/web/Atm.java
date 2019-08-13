package com.yuriikoval1997.atm.web;

import com.yuriikoval1997.atm.data.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Atm {
    private final AtmRepository atmRepo;

    @Autowired
    public Atm(AtmRepository atmRepo) {
        this.atmRepo = atmRepo;
    }
}
