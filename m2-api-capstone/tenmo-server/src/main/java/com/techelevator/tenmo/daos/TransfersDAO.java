package com.techelevator.tenmo.daos;

import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.Users;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public interface TransfersDAO {

    List<Users> displayUsers();
    void sendBucks(Principal principal, Transfers transfers);
    void sendTo(long transferTo, double transferAmount);
    void sendFrom(String accountFrom, double transferAmount);
    void addTransfers(String accountFrom, long transferTo, double transferAmount);


}
