package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.dto.DatoRequest;
import com.kadTI.techHeart_backend.dto.SesionHistorialDTO;
import com.kadTI.techHeart_backend.dto.IniciarSesionRequest;
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
    public ResponseEntity<?> inciarSesion(@RequestBody IniciarSesionRequest dto, Principal principal){
        SesionECG sesion = sesionService.iniciarSesion(dto.getIdPaciente(), principal.getName());
        return ResponseEntity.ok("Sesion iniciada con ID: " + sesion.getId());
    }

    @PostMapping("/finalizar/{idSesion}")
    public ResponseEntity<?> finalizarSesion(@PathVariable Long idSesion) {
        sesionService.finalizarSesion(idSesion);
        return ResponseEntity.ok("Sesión finalizada correctamente");
    }

    @PostMapping("/guardar-dato/{idSesion}")
    public ResponseEntity<?> guardarDato(@PathVariable Long idSesion, @RequestBody DatoRequest datoRequest) {
        sesionService.guardarDatoEnSesion(idSesion, datoRequest.getValor());
        return ResponseEntity.ok("Dato guardado correctamente en la sesión " + idSesion);
    }

    @GetMapping("/historial/{idPaciente}")
    public ResponseEntity<List<SesionHistorialDTO>> obtenerHistorial(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(sesionService.obtenerHistorial(idPaciente));
    }

    @GetMapping("/{idSesion}")
    public ResponseEntity<SesionECG> obtenerSesion(@PathVariable Long idSesion) {
        return ResponseEntity.ok(sesionService.BuscarSesion(idSesion));
    }

    @GetMapping("/totalSesiones")
    public long sesionesRealizadas(){
        return sesionService.totalSesiones();
    }

}

