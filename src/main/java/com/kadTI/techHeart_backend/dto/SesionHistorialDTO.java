package com.kadTI.techHeart_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SesionHistorialDTO {

    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private String nombrePaciente;
    private String nombreMedico;
    private List<DatoRequest> datos;

}
