package com.techelevator.tenmo.daos;
import com.techelevator.tenmo.auth.model.User;
import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTransfersDAO implements TransfersDAO{

    private JdbcTemplate jdbcTemplate;
    private Users users;


    public JdbcTransfersDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Users> displayUsers() {

        List<Users> userList = new ArrayList<Users>();

        String sql = "SELECT user_id, username FROM users";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        if (rows.next()) {
            users.setUserID(rows.getLong("user_id"));
            users.setUsername(rows.getString("username"));
            userList.add(users);
        }
        return userList;
    }


    @Override
    public void sendBucks() {
        String sql = "";
    }


}
