package com.kadTI.techHeart_backend.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends Usuario{

        @Column
        private String especialidad;

        @Column
        private Date fechaGraduacion;

        @Column
        private String lugarGraduacion;


}
