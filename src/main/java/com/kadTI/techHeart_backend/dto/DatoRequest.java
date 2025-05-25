package com.kadTI.techHeart_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoRequest {
    private int valor;
    private LocalDateTime timestamp;
}
