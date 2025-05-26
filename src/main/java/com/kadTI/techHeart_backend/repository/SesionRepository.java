package com.kadTI.techHeart_backend.repository;

import com.kadTI.techHeart_backend.entity.SesionECG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<SesionECG, Long> {

    @Query("SELECT s FROM SesionECG s WHERE s.paciente.id = :idPaciente")
    List<SesionECG> obtenerSesionesPorPaciente(@Param("idPaciente") Long idPaciente);

}
