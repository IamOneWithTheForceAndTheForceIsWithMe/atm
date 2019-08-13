package com.yuriikoval1997.atm.controllers;

import com.yuriikoval1997.atm.pojoes.Account;
import com.yuriikoval1997.atm.pojoes.UserForm;
import com.yuriikoval1997.atm.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private AccountRepo accountRepo;

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String processUserForm(@Valid UserForm userForm, Errors errors){
        if (errors.hasErrors()){
            return "registration";
        }
        Account account = accountRepo.findUserByUsername(userForm.getUsername());
        if (account != null){
            return "registration";
        }
        accountRepo.createAccount(userForm);
        return "registration:/accounts";
    }
}
