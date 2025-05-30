package com.kadTI.techHeart_backend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente extends Usuario{

        @Column
        private String tipoSangre;

        @Column
        private String condicionesMedicas;

        @Column
        private Long telefono;

}
