package com.kadTI.techHeart_backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends Usuario{

        @Column
        @NotBlank(message = "La especialidad es obligatoria")
        @Size(min = 3, max = 100, message = "La especialidad debe tener entre 3 y 100 caracteres")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "La especialidad solo puede contener letras")
        private String especialidad;

        @Column
        @NotNull(message = "La fecha de graduación es obligatoria")
        @Past(message = "La fecha de graduación debe ser en el pasado")
        private LocalDate fechaGraduacion;

        @Column
        @NotBlank(message = "El lugar de graduación es obligatorio")
        @Size(min = 3, max = 200, message = "El lugar de graduación debe tener entre 3 y 200 caracteres")
        private String lugarGraduacion;


}
