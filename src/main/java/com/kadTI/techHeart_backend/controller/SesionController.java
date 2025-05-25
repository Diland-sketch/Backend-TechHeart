package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.dto.SesionHistorialDTO;
import com.kadTI.techHeart_backend.dto.SesionRequest;
import com.kadTI.techHeart_backend.entity.SesionECG;
import com.kadTI.techHeart_backend.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/sesion")
public class SesionController {

    @Autowired
    SesionService sesionService;

    @PostMapping("/iniciar")
    public ResponseEntity<?> inciarSesion(@RequestBody SesionRequest dto, Principal principal){
        SesionECG sesion = sesionService.iniciarSesion(dto.getIdPaciente(), principal.getName());
        return ResponseEntity.ok("Sesion iniciada con ID: " + sesion.getId());
    }

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizarSesion() {
        sesionService.finalizarSesion();
        return ResponseEntity.ok("Sesión finalizada correctamente");
    }

    @PostMapping("/guardar-dato")
    public ResponseEntity<?> guardarDato(@RequestBody int valor) {
        sesionService.guardarDatosSimulado(valor); // más adelante puedes cambiarlo a tiempo real desde ESP32
        return ResponseEntity.ok("Dato guardado");
    }

    @GetMapping("/historial/{idPaciente}")
    public ResponseEntity<List<SesionHistorialDTO>> obtenerHistorial(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(sesionService.obtenerHistorial(idPaciente));
    }

    @GetMapping("/actual")
    public ResponseEntity<?> obtenerSesionActiva() {
        return ResponseEntity.ok(sesionService.getSesionActiva());
    }

    @GetMapping("/totalSesiones")
    public long sesionesRealizadas(){
        return sesionService.totalSesiones();
    }

}

