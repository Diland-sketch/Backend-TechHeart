package com.kadTI.techHeart_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoECG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int valor;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "sesion_id")
    @JsonBackReference
    private SesionECG sesion;

}
