package com.techelevator.tenmo.daos;

import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.Users;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public interface TransfersDAO {

    List<Users> displayUsers();
    void sendTo(long transferTo, double transferAmount);
    void sendFrom(String accountFrom, double transferAmount);
    void updateTransfers(Principal principal, int transferTo, double transferAmount);


}
