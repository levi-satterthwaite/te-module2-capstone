package com.techelevator.tenmo.services;

import com.techelevator.tenmo.auth.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Users;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TransfersService {

    private String baseUrl;
    private AuthenticatedUser currentUser;
    private RestTemplate restTemplate = new RestTemplate();

    public TransfersService(AuthenticatedUser currentUser, String baseUrl) {
        this.currentUser = currentUser;
        this.baseUrl = baseUrl;
    }

    public TransfersService(){

    }

    public List<Users> getUsers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity(headers);

        List usersList = restTemplate.exchange(baseUrl + "getusers",
                HttpMethod.GET, entity, List.class).getBody();

     return usersList;
    }
}
