package com.kadTI.techHeart_backend.service;

import com.kadTI.techHeart_backend.entity.Rol;
import com.kadTI.techHeart_backend.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol crearRol(Rol rol){
        return rolRepository.save(rol);
    }

    public Rol buscarPorId(Long id){
        return rolRepository.findById(id).orElse(null);
    }

}
