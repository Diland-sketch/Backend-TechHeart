package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.dto.LoginRequest;
import com.kadTI.techHeart_backend.dto.LoginResponse;
import com.kadTI.techHeart_backend.dto.RolDTO;
import com.kadTI.techHeart_backend.dto.UsuarioDTO;
import com.kadTI.techHeart_backend.entity.Rol;
import com.kadTI.techHeart_backend.entity.Usuario;
import com.kadTI.techHeart_backend.security.JwtUtil;
import com.kadTI.techHeart_backend.service.RolService;
import com.kadTI.techHeart_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/crear")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        if(usuario.getRol() != null && usuario.getRol().getIdRol() != null){
            Rol rol = rolService.buscarPorId(usuario.getRol().getIdRol());
            if (rol == null){
                return ResponseEntity.badRequest().body(null);
            }
            usuario.setRol(rol);
        }
        usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/obtenerTodos")
    public ResponseEntity<List<Usuario>> obtenerUsuarios(){
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        Usuario usuario = usuarioService.login(loginRequest.getNombreUsuario(), loginRequest.getContraseña());

        if (usuario != null){
            String token = jwtUtil.generateToken(usuario.getNombreUsuario(), usuario.getRol().getNombreRol());

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setIdentificacion(usuario.getIdentificacion());
            usuarioDTO.setPrimerNombre(usuario.getPrimerNombre());
            usuarioDTO.setSegundoNombre(usuario.getSegundoNombre());
            usuarioDTO.setPrimerApellido(usuario.getPrimerApellido());
            usuarioDTO.setSegundoApellido(usuario.getSegundoApellido());

            RolDTO rolDTO = new RolDTO();
            rolDTO.setIdRol(usuario.getRol().getIdRol());
            rolDTO.setNombreRol(usuario.getRol().getNombreRol());

            usuarioDTO.setRol(rolDTO);

            return ResponseEntity.ok(new LoginResponse("Inicio de sesión exitoso", usuarioDTO, token));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Credenciales incorrectas", null, null));
        }
    }

    @GetMapping("/protegido")
    public ResponseEntity<String> protegido(){
        return ResponseEntity.ok("Acceso autorizado al endpoint protegido");
    }


}
