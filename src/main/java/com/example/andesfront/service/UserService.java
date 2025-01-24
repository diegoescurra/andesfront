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
    RestTemplate restTemplate;

    private UserDTO userDTO;
    private String ruta;

    public String signin(UserDTO userDTO) {
        ruta = "http://localhost:9001/auth/login";
            ResponseEntity<String> response = restTemplate.postForEntity(ruta, userDTO, String.class);
            System.out.println(response .getBody());
            return response.getBody();

    }
}
