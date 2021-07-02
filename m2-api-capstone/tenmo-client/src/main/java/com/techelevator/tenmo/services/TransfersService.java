package com.techelevator.tenmo.services;

import com.techelevator.tenmo.auth.models.AuthenticatedUser;
import com.techelevator.tenmo.auth.models.User;
import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.Users;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransfersService {

    private String baseUrl;
    private AuthenticatedUser currentUser;
    private RestTemplate restTemplate = new RestTemplate();

    public TransfersService(AuthenticatedUser currentUser, String baseUrl) {
        this.currentUser = currentUser;
        this.baseUrl = baseUrl;
    }

    public List<Users> getUsers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity(headers);

//        usersList = restTemplate.getForObject(baseUrl + "/getusers", );
        Users[] usersList = restTemplate.exchange(baseUrl + "getusers",
                    HttpMethod.GET, entity, Users[].class).getBody();

     return Arrays.asList(usersList);
    }


    public void sendBucks(int transferTo, double transferAmount){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity(headers);

        Transfers transfers = new Transfers();
        transfers.setAccountToId((long)transferTo);
        transfers.setAmount(transferAmount);

        restTemplate.exchange(baseUrl + "sendbucks", HttpMethod.POST, entity, transfers.getClass());
//        restTemplate.postForEntity(baseUrl + "sendbucks", objects);
    }
}
