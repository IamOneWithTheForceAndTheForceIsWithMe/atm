package com.yuriikoval1997.atm.repositories;

import com.yuriikoval1997.atm.pojoes.CreditCard;
import com.yuriikoval1997.atm.services.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Slf4j
@Repository
public class CreditCardRepoImpl implements CreditCardRepo {

    private JdbcTemplate jdbc;

    @Autowired
    public CreditCardRepoImpl(JdbcTemplate jdbc, CreditCardService creditCardService) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<CreditCard> findCreditCards(Long id) {
        return jdbc.query("SELECT cc_number, cc_expiration FROM credit_cards WHERE account_id = ?",
                this::mapToRow,
                id);
    }

    @Override
    public CreditCard findCreditCardByNumber(String ccNumber) {
        return jdbc.queryForObject("SELECT cc_number, cc_expiration, cc_cvv " +
                        "FROM credit_cards WHERE cc_number = ?",
                this::mapToRow,
                ccNumber);
    }

    private CreditCard mapToRow(ResultSet rs, int rowNum) throws SQLException {
        CreditCard creditCard = new CreditCard();
        creditCard.setCcNumber(rs.getString("cc_number"));
        creditCard.setCcExpiration(rs.getString("cc_expiration"));
        return creditCard;
    }

    @Override
    public Status put(String ccNumber, long amount) {
        try {
            jdbc.update("UPDATE credit_cards SET credits = credits + amount WHERE cc_number = ?", amount, ccNumber);
            log.info(amount + " has been put on credit card "+ ccNumber);
            return Status.SUCCESS;
        } catch (DataAccessException e){
            return Status.NO_SUCH_CREDIT_CARD;
        }
    }

    @Override
    public Status withdraw(String ccNumber, long amount) {
        Long credits = jdbc.queryForObject("SELECT credits FROM credit_cards WHERE cc_number = ?",
                (rs, n) -> rs.getLong("credits"),
                amount);
        if (credits == null){
            return Status.NO_SUCH_CREDIT_CARD;
        }
        if (credits > amount) {
            return Status.NOT_ENOUGH_CREDITS;
        }
        jdbc.update("UPDATE credit_cards SET credits = credits - amount WHERE cc_number = ?", amount, ccNumber);
        log.info(amount + " has been withdraw from credit card " + ccNumber);
        return Status.SUCCESS;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            timeout = 100)
    @Override
    public Status transfer(String from, String to, long amount) {
        if (amount <= 0 ){
            throw new IllegalArgumentException();
        }
        withdraw(from, amount);
        put(to, amount);
        log.info(amount + " has been transferred from credit card " + from + " to " + to);
        return Status.SUCCESS;
    }
}
