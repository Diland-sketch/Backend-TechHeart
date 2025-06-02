package com.kadTI.techHeart_backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SesionECG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    private LocalDateTime inicio;

    @PastOrPresent(message = "La fecha de fin no puede ser futura")
    private LocalDateTime fin;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @NotNull(message = "El paciente es obligatorio")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @NotNull(message = "El médico es obligatorio")
    private Medico medico;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DatoECG> datos;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    private List<Incidente> incidentes;


}
