package com.kadTI.techHeart_backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacienteDTO {

    private String nombreUsuario;
    private String email;
    private String identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private RolDTO rol;
}
