package com.techelevator.tenmo.daos;

import com.techelevator.tenmo.models.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcAccountDAO implements AccountDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public Account getUserAccount(String username) {
        Account account = null;

        String sql = "Select account_id, account.user_id, balance"
        + "FROM accounts JOIN users ON users.user_id = accounts.user_id WHERE users.username = ?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, username);

        if (rows.next()) {
           account = new Account();
           account.setAccountId(rows.getLong("account_id"));
           account.setUserId(rows.getLong("user_id"));
           account.setBalance(rows.getDouble("balance"));
        }

        return account;
    }
}
