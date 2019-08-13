package com.yuriikoval1997.atm.repositories;

import com.yuriikoval1997.atm.pojoes.Account;
import com.yuriikoval1997.atm.pojoes.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccountRepoImpl implements AccountRepo{

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createAccount(UserForm userForm) {
        jdbc.update("INSERT INTO accounts (username, password) VALUES (?, ?)",
                userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()));
    }

    @Override
    public Account login(UserForm userForm) {
        return jdbc.queryForObject("SELECT username, credit_card_number, credits FROM accounts WHERE username = ?",
                this::mapRowToAccount,
                userForm.getUsername());
    }

    @Override
    public Account findUserByUsername(String username) {
        return jdbc.queryForObject("SELECT username, credit_card_number, credits FROM accounts WHERE username = ?",
                this::mapRowToAccount,
                username);
    }

    private Account mapRowToAccount(ResultSet rs, int i) throws SQLException {
        return new Account(
                rs.getString("username"),
                rs.getString("credit_card_number"),
                Long.parseLong(rs.getString("credits"))
        );
    }
}
