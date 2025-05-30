package com.kadTI.techHeart_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoRequest {

    @NotNull(message = "El valor del dato ECG es obligatorio")
    private int valor;
    private LocalDateTime timestamp;
}
