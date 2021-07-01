package com.techelevator.tenmo.daos;

import com.techelevator.tenmo.models.Account;

public interface AccountDAO {

    Account getUserAccount(String username);

}
