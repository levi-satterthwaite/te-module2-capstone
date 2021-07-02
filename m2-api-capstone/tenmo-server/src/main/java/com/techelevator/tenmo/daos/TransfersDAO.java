package com.techelevator.tenmo.daos;

import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.Users;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public interface TransfersDAO {

    List<Users> displayUsers();
    void sendTo(int transferTo, double transferAmount);
    void sendFrom(Principal principal, double transferAmount);
    void updateTransfers(Principal principal, int transferTo, double transferAmount);


}
