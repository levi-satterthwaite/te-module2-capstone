package com.techelevator.tenmo.controllers;

import com.techelevator.tenmo.daos.TransfersDAO;
import com.techelevator.tenmo.models.Transfers;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.techelevator.tenmo.models.Users;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransfersController {

    private TransfersDAO transfersDAO;

    public TransfersController(TransfersDAO transfersDAO) {
        this.transfersDAO = transfersDAO;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/getusers", method = RequestMethod.GET)
    public List<Users> get(Principal principal) {
        return transfersDAO.displayUsers();
    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/sendbucks", method = RequestMethod.POST)
    public void post(@RequestBody Transfers transfers, Principal principal){
        transfersDAO.sendBucks(principal, transfers);
     }
}


