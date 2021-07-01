package com.techelevator.tenmo.services;

import com.techelevator.tenmo.auth.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class AccountService {

    private String baseUrl;
    private AuthenticatedUser currentUser;
    private RestTemplate restTemplate = new RestTemplate();

    public AccountService(AuthenticatedUser currentUser, String baseUrl) {
        this.currentUser = currentUser;
        this.baseUrl = baseUrl;
    }

    public Account getUserAccount(){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity(headers);


        Account account = restTemplate.exchange(baseUrl + "accounts",
                HttpMethod.GET, entity, Account.class).getBody();

        return account;
    }
}
