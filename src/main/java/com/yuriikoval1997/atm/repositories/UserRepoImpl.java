package com.yuriikoval1997.atm.repositories;

import com.yuriikoval1997.atm.pojoes.CreditCard;
import com.yuriikoval1997.atm.pojoes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

@Repository
public class UserRepoImpl implements UserRepo {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public User findUserByUsername(String username) {
        return jdbc.queryForObject("SELECT * FROM accounts WHERE username = ?",
                this::mapRowToAccount,
                username);
    }

    private User mapRowToAccount(ResultSet rs, int i) throws SQLException {
        return new User(rs.getString("id"),
                rs.getString("username"),
                rs.getString("email")
        );
    }

    @Override
    public void register(User user) {
        String statement = "INSERT INTO accounts (username, password, email) VALUES(?, ?, ?)";
        jdbc.update(statement, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public Long addCreditCard(User user, CreditCard creditCard) {
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO accounts (cc_number, cc_expiration, cc_cvv, account_id) values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                creditCard.getCcNumber(),
                                creditCard.getCcExpiration(),
                                creditCard.getCcCVV(),
                                user.getId()
                        )
                );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        if (keyHolder.getKey() == null){
            return (long) -1;
        }
        return keyHolder.getKey().longValue();
    }
}
