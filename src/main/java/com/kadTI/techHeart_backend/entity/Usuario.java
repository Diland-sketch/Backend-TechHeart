package com.kadTI.techHeart_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    private String contraseña;

    @Column(nullable = false, unique = true)
    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @Column(nullable = false)
    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    @Column(nullable = false)
    @NotBlank(message = "El primer nombre es obligatorio")
    private String primerNombre;

    @Column
    private String segundoNombre;

    @Column(nullable = false)
    @NotBlank(message = "El primer apellido es obligatorio")
    private String primerApellido;

    @Column
    private String segundoApellido;

    @Column(nullable = false)
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    private Integer edad;

    @Column(nullable = false)
    @Pattern(regexp = "[MF]", message = "El sexo debe ser M o F")
    private String sexo;

}
