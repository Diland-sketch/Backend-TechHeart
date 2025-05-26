package com.kadTI.techHeart_backend.dto;

import lombok.Data;

@Data
public class IniciarSesionRequest {
    private Long idPaciente;
    private String usernameMedico;
}
