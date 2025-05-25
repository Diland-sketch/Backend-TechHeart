package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.dto.DatoECGDTO;
import com.kadTI.techHeart_backend.dto.DatoRequest;
import com.kadTI.techHeart_backend.service.DatoService;
import com.kadTI.techHeart_backend.service.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dato")
@CrossOrigin(origins = "*")
public class DatoController {

    @Autowired
    private DatoService datoService;

    @PostMapping("/sesion/{idSesion}")
    public ResponseEntity<String> recibirDato(@PathVariable Long idSesion, @RequestBody DatoECGDTO dto) {
        datoService.guardarDatoParaSesion(idSesion, dto);
        return ResponseEntity.ok("Dato recibido correctamente");
    }

}
