package com.techelevator.tenmo.controllers;

import com.techelevator.tenmo.daos.TransfersDAO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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



    @RequestMapping(path="/sendbucks", method = RequestMethod.POST)
    public void post(Principal principal, int transferTo, double transferAmount){
        transfersDAO.sendTo(transferTo, transferAmount);
        transfersDAO.sendFrom(principal, transferAmount);
     }
}


