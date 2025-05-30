package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.dto.DatoECGDTO;
import com.kadTI.techHeart_backend.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dato")
@CrossOrigin(origins = "*")
public class DatoController {

    @Autowired
    private SesionService sesionServiceService;

    @PostMapping("/sesion/{idSesion}")
    public ResponseEntity<String> recibirDato(@PathVariable Long idSesion, @RequestBody DatoECGDTO dto) {
        sesionServiceService.guardarDatoEnSesion(idSesion, dto.getValor());
        return ResponseEntity.ok("Dato recibido y enviado por WebSocket");
    }

}
