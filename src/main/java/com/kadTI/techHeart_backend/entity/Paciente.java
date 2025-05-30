package com.kadTI.techHeart_backend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente extends Usuario{

        @Column
        @Pattern(regexp = "^(A|B|AB|O)[+-]$",
                message = "El tipo de sangre debe ser válido (A+, A-, B+, B-, AB+, AB-, O+, O-)")
        private String tipoSangre;

        @Column(length = 1000)
        @Size(max = 1000, message = "Las condiciones médicas no pueden exceder 1000 caracteres")
        private String condicionesMedicas;

}
