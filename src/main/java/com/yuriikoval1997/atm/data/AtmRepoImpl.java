package com.yuriikoval1997.atm.data;

import com.yuriikoval1997.atm.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class AtmRepoImpl implements AtmRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public AtmRepoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean putMoney(long amount, Account acc) {
        if (! searchUser(acc)){
            return false;
        }
        acc.setCredits(acc.getCredits() + amount);
        jdbc.update("UPDATE accounts SET credits = ? WHERE id = ?", acc.getId());
        return true;
    }

    private boolean searchUser(Account acc) {
        List<Account> list = jdbc.query("SELECT * FROM accounts WHERE id = ?", this::mapToRowToObj);
        return ! list.isEmpty();
    }

    private Account mapToRowToObj(ResultSet rs, int i) throws SQLException {
        return new Account(Long.valueOf(rs.getString("id")));
    }

    @Override
    public Optional<Long> withdraw(long amount, Account acc) {
        if (! searchUser(acc)){
            return Optional.empty();
        }
        if (acc.getCredits() < amount){
            return Optional.empty();
        }
        acc.setCredits(acc.getCredits() - amount);
        jdbc.update("UPDATE accounts SET credits ? WHERE id = ?", acc.getCredits());
        return Optional.of(amount);
    }

    @Override
    public boolean transferToAnotherAccount(long amount, Account from, Account to) {
        return false;
    }
}
