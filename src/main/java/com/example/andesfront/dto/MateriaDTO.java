package com.example.andesfront.dto;

import lombok.Data;

@Data
public class MateriaDTO {

    private Long id;
    private String nombre;
    private AlumnoDTO alumnoDTO;
}
