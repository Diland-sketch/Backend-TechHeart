package com.kadTI.techHeart_backend.dto;

import com.kadTI.techHeart_backend.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String mensaje;
    private UsuarioDTO usuario;
    private String token;
}
