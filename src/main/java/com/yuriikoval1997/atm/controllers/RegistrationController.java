package com.yuriikoval1997.atm.controllers;

import com.yuriikoval1997.atm.pojoes.RegistrationForm;
import com.yuriikoval1997.atm.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String processUserForm(@Valid RegistrationForm registrationForm, Errors errors){
        if (errors.hasErrors()){
            return "registration";
        }
        userRepo.register(registrationForm.toUser(passwordEncoder));
        return "registration:/login";
    }
}
