package com.yuriikoval1997.atm.pojoes;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class RegistrationForm {

    @Size(min = 5, message = "User name has to have at least 5 characters!")
    private final String username;

    @Size(min = 8, message = "Password requires at least 8 characters!")
    private final String password;

    @Pattern(regexp = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$", message = "Invalid email address!")
    private final String email;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, email, passwordEncoder.encode(password));
    }

}
