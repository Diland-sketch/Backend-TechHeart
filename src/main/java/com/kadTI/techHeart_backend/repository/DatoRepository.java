package com.kadTI.techHeart_backend.repository;

import com.kadTI.techHeart_backend.entity.DatoECG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoRepository extends JpaRepository<DatoECG, Long> {
}
