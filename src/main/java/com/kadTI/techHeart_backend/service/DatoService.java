package com.kadTI.techHeart_backend.service;

import com.kadTI.techHeart_backend.dto.DatoECGDTO;
import com.kadTI.techHeart_backend.entity.DatoECG;
import com.kadTI.techHeart_backend.entity.SesionECG;
import com.kadTI.techHeart_backend.repository.DatoRepository;
import com.kadTI.techHeart_backend.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DatoService {

    @Autowired
    private DatoRepository datoRepository;

    @Autowired
    private SesionRepository sesionRepository;


    public void guardarDatoParaSesion(Long idSesion, DatoECGDTO dto){
        SesionECG sesionECG = sesionRepository.findById(idSesion)
                .orElseThrow(() -> new RuntimeException("Sesion no encotrada"));

        DatoECG datoECG = new DatoECG();
        datoECG.setValor((dto.getValor()));
        datoECG.setTimestamp(LocalDateTime.now());
        datoECG.setSesion(sesionECG);

        datoRepository.save(datoECG);
    }

}
