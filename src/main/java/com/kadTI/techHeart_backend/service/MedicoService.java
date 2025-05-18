package com.kadTI.techHeart_backend.service;

import com.kadTI.techHeart_backend.entity.Medico;
import com.kadTI.techHeart_backend.entity.Paciente;
import com.kadTI.techHeart_backend.repository.MedicoRepository;
import com.kadTI.techHeart_backend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Medico> verTodos(){
        return medicoRepository.findAll();
    }

    public Medico CrearMedico(Medico medico){
        String contraseñaEncriptada = passwordEncoder.encode(medico.getContraseña());
        medico.setContraseña(contraseñaEncriptada);
        return medicoRepository.save(medico);
    }

    public Medico BuscarMedico(Long id){

        Optional<Medico> OpcionalMedico   =  medicoRepository.findById(id);
        if(OpcionalMedico.isPresent()){
            Medico medico = OpcionalMedico.get();
            return medico;
        }
        return null;
    }

    public Medico Modificar(Medico medico){
        return medicoRepository.save(medico);
    }

    public void Eliminar(Medico medico){
        medicoRepository.delete(medico);
    }
}
