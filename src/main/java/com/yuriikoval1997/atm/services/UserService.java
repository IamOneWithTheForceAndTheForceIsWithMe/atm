package com.yuriikoval1997.atm.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException;

    boolean userHasCreditCard(String username, String ccNUmber);
}
