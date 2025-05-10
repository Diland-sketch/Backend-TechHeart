package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.entity.Rol;
import com.kadTI.techHeart_backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping("/crear")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol){
        rolService.crearRol(rol);
        return ResponseEntity.ok(rol);
    }

}
