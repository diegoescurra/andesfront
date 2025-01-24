package com.example.andesfront.service;

import com.example.andesfront.dto.AlumnoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    RestTemplate restTemplate;

    private AlumnoDTO alumnoDTO;
    private final String API_URL = "http://localhost:9001/api/v1/alumnos/lista_alumnos";;

    public List<AlumnoDTO> getAlumnos(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("El token no está disponible en la sesión");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);
        // Realizar la solicitud al backend
        ResponseEntity<List<AlumnoDTO>> response = restTemplate.exchange(
                "http://localhost:9001/api/v1/alumnos/lista_alumnos",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<AlumnoDTO>>() {}
        );
        return response.getBody();
    }
}
