package com.example.andesfront.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AlumnoDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String direccion;
    private Set<MateriaDTO> materiaList;

}
