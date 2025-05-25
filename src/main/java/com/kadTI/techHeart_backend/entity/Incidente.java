package com.kadTI.techHeart_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String descripcion;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "sesion_id")
    private SesionECG sesion;

}

