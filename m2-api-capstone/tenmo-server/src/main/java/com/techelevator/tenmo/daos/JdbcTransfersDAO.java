package com.techelevator.tenmo.daos;
import com.techelevator.tenmo.auth.model.User;
import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransfersDAO implements TransfersDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransfersDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Users> displayUsers() {

        List<Users> userList = new ArrayList<Users>();

        String sql = "SELECT user_id, username FROM users";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while (rows.next()) {
            Users users = new Users();
            users.setUserID(rows.getLong("user_id"));
            users.setUsername(rows.getString("username"));
            userList.add(users);
        }
        return userList;
    }

    @Override
    public void sendBucks(Principal principal, Transfers transfers) {
        sendTo(transfers.getAccountToId(), transfers.getAmount());
        sendFrom(principal.getName(), transfers.getAmount());
        addTransfers(principal.getName(), transfers.getAccountToId(), transfers.getAmount());
    }

    @Override
    public void sendTo(long transferTo, double transferAmount) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE user_id = ?";
        jdbcTemplate.update(sql, transferAmount, transferTo);    }

    @Override
    public void sendFrom(String accountFrom, double transferAmount) {
        String sql = "UPDATE accounts SET balance = balance - ? FROM users WHERE accounts.user_id = users.user_id AND users.username = ?";
        jdbcTemplate.update(sql, transferAmount, accountFrom);
    }

    @Override
    public void addTransfers(String accountFrom, long transferTo, double transferAmount) {
        String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                " VALUES (default, 2, 2, (SELECT accounts.account_id FROM accounts JOIN users ON accounts.user_id = users.user_id WHERE users.username = ?), (SELECT account_id FROM accounts WHERE user_id = ?), ?)";
        jdbcTemplate.update(sql, accountFrom, transferTo, transferAmount);
    }

}
