package com.example.andesfront.service;

import com.example.andesfront.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private UserDTO userDTO;
    private final String API_URL = "http://localhost:9001/auth/login";;

    public String signin(UserDTO userDTO) {
        try {

            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, userDTO, String.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Login fallido "+ response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la API REST "+ e);
        }

    }
}
