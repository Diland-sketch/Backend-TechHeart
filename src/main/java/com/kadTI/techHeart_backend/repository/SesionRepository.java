package com.kadTI.techHeart_backend.repository;

import com.kadTI.techHeart_backend.entity.Paciente;
import com.kadTI.techHeart_backend.entity.SesionECG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<SesionECG, Long> {

    List<SesionECG> findByPaciente(Paciente paciente);

}
