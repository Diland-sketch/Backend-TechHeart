package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.entity.Rol;
import com.kadTI.techHeart_backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/obtenerRoles")
    public ResponseEntity<List<Rol>> obtenerRoles(){
        List<Rol> roles = rolService.obtenerRoles();
        return ResponseEntity.ok(roles);
    }


}
