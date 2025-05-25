package com.kadTI.techHeart_backend.repository;

import com.kadTI.techHeart_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    @Query("SELECT EXTRACT(MONTH FROM u.fechaRegistro) AS mes, COUNT(u.identificacion) " +
            "FROM Usuario u GROUP BY EXTRACT(MONTH FROM u.fechaRegistro) ORDER BY mes")
    List<Object[]> contarUsuariosPorMes();
}
