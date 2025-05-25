package com.kadTI.techHeart_backend.repository;

import com.kadTI.techHeart_backend.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByNombreUsuario(String nombreUsuario);

}
