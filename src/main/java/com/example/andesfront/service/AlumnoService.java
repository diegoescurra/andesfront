package com.example.andesfront.service;

import com.example.andesfront.dto.AlumnoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AlumnoService {

    @Autowired
    RestTemplate restTemplate;

    private AlumnoDTO alumnoDTO;
    private HttpEntity<AlumnoDTO> request = new HttpEntity<>(alumnoDTO);
    private String ruta;

    public List<AlumnoDTO> getAlumnos() {
        ruta = "http://localhost:9001/api/v1/alumnos/lista_alumnos";
        ResponseEntity<List<AlumnoDTO>> response = restTemplate.exchange(ruta, HttpMethod.GET,
                request, new ParameterizedTypeReference<List<AlumnoDTO>>() {});
        return response.getBody();
    }
}
