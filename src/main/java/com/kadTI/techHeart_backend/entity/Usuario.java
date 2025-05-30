package com.kadTI.techHeart_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @Column(nullable = false, unique = true)
    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "^[0-9]{8,12}$", message = "La identificación debe tener entre 8 y 12 dígitos")
    private String identificacion;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 30, message = "El nombre de usuario debe tener entre 3 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "El nombre de usuario solo puede contener letras, números y guiones bajos")
    private String nombreUsuario;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 5, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    private String contraseña;

    @Column(nullable = false, unique = true)
    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    @NotNull(message = "El rol es obligatorio")
    private Rol rol;

    @Column(nullable = false)
    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El primer nombre debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El primer nombre solo puede contener letras")
    private String primerNombre;

    @Column
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*$", message = "El segundo nombre solo puede contener letras")
    private String segundoNombre;

    @Column(nullable = false)
    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El primer apellido debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El primer apellido solo puede contener letras")
    private String primerApellido;

    @Column
    @Size(max = 50, message = "El segundo apellido no puede exceder 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*$", message = "El segundo apellido solo puede contener letras")
    private String segundoApellido;

    @Column(nullable = false)
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    @Max(value = 120, message = "La edad no puede ser mayor a 120 años")
    private Integer edad;

    @Column(nullable = false)
    @NotBlank(message = "El sexo es obligatorio")
    @Pattern(regexp = "[MF]", message = "El sexo debe ser M o F")
    private String sexo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

}
