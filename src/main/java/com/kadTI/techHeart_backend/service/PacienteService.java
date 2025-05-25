package com.kadTI.techHeart_backend.service;

import com.kadTI.techHeart_backend.dto.PacienteDTO;
import com.kadTI.techHeart_backend.entity.Paciente;
import com.kadTI.techHeart_backend.entity.Usuario;
import com.kadTI.techHeart_backend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Paciente> verTodos(){
        return pacienteRepository.findAll();
    }

    public Paciente CrearPaciente(Paciente paciente){
        String contraseñaEncriptada = passwordEncoder.encode(paciente.getContraseña());
        paciente.setContraseña(contraseñaEncriptada);
        return pacienteRepository.save(paciente);
    }

    public Paciente BuscarPaciente(Long id){

        Optional<Paciente> OpcionalPaciente   =  pacienteRepository.findById(id);
        if(OpcionalPaciente.isPresent()){
            Paciente paciente = OpcionalPaciente.get();
            return paciente;
        }
        return null;
    }

    public Paciente Modificar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void Eliminar(Paciente paciente){
        pacienteRepository.delete(paciente);
    }

    public long contarPacientes(){
        return pacienteRepository.count();
    }

}
