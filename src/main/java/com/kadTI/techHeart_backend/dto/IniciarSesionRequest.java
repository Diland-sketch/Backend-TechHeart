package com.kadTI.techHeart_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IniciarSesionRequest {
    @NotNull(message = "El ID del paciente es obligatorio")
    @Positive(message = "El ID del paciente debe ser un número positivo")
    private Long idPaciente;

    @NotNull(message = "El Username del paciente es obligatorio")
    private String usernameMedico;
}
