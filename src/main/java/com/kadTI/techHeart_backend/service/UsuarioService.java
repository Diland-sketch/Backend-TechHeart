package com.kadTI.techHeart_backend.service;

import com.kadTI.techHeart_backend.entity.Usuario;
import com.kadTI.techHeart_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(Usuario usuario){
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(contraseñaEncriptada);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario login(String nombreUsuario, String contraseña){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            if(passwordEncoder.matches(contraseña, usuario.getContraseña())){
                return usuario;
            }
        }
        return null;
    }

}
