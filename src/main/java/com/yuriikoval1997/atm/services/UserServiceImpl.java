package com.yuriikoval1997.atm.services;

import com.yuriikoval1997.atm.pojoes.CreditCard;
import com.yuriikoval1997.atm.pojoes.User;
import com.yuriikoval1997.atm.repositories.CreditCardRepo;
import com.yuriikoval1997.atm.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private CreditCardRepo creditCardRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, CreditCardRepo creditCardRepo) {
        this.userRepo = userRepo;
        this.creditCardRepo = creditCardRepo;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user != null){
            return user;
        }
        throw new UsernameNotFoundException("Username " + username + " doesn't exist!");
    }

    @Override
    public boolean userHasCreditCard(String username, String ccNumber) {
        User user = loadUserByUsername(username);
        Collection<CreditCard> creditCard = creditCardRepo.findCreditCards(user.getId());
        return creditCard
                .stream()
                .anyMatch(cc -> cc.getCcNumber().equals(ccNumber));
    }
}
