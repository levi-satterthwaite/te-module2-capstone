package com.techelevator.tenmo.auth.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Component
public class JDBCBalanceDAO implements BalanceDAO{

    private JdbcTemplate jdbcTemplate;

    public JDBCBalanceDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

   @Override
   public BigDecimal returnBalance(){

        BigDecimal balance = new BigDecimal(0.0);
        return balance;
    }
}
