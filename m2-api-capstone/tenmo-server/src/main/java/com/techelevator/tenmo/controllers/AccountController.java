package com.techelevator.tenmo.controllers;

import com.techelevator.tenmo.daos.AccountDAO;
import com.techelevator.tenmo.models.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

    private AccountDAO accountDAO;

    public AccountController(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    @RequestMapping(path="/accounts", method = RequestMethod.GET)
    public Account get(Principal principal) {
        return this.accountDAO.getUserAccount(principal.getName());
    }

}
