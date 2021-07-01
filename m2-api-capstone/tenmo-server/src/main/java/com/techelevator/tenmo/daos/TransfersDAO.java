package com.techelevator.tenmo.daos;

import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.Users;

import java.util.ArrayList;
import java.util.List;

public interface TransfersDAO {

    List<Users> displayUsers();
    void sendBucks();

}
