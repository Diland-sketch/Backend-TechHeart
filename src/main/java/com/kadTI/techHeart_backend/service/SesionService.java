package com.kadTI.techHeart_backend.service;

import com.kadTI.techHeart_backend.dto.DatoRequest;
import com.kadTI.techHeart_backend.dto.SesionHistorialDTO;
import com.kadTI.techHeart_backend.entity.DatoECG;
import com.kadTI.techHeart_backend.entity.Medico;
import com.kadTI.techHeart_backend.entity.Paciente;
import com.kadTI.techHeart_backend.entity.SesionECG;
import com.kadTI.techHeart_backend.repository.DatoRepository;
import com.kadTI.techHeart_backend.repository.MedicoRepository;
import com.kadTI.techHeart_backend.repository.PacienteRepository;
import com.kadTI.techHeart_backend.repository.SesionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;
    @Autowired
    private DatoRepository datoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    private SesionECG sesionActiva;

    public SesionECG iniciarSesion(Long idPaciente, String usernameMedico){
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Medico medico = medicoRepository.findByNombreUsuario(usernameMedico)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        sesionActiva = new SesionECG();
        sesionActiva.setInicio(LocalDateTime.now());
        sesionActiva.setPaciente(paciente);
        sesionActiva.setMedico(medico);

        return sesionRepository.save(sesionActiva);
    }

    public void guardarDatosSimulado(int valor) {
        if(sesionActiva == null){
            sesionActiva = new SesionECG();
            sesionActiva.setInicio(LocalDateTime.now());
            sesionActiva = sesionRepository.save(sesionActiva);
        }

        DatoECG dato = new DatoECG();
        dato.setValor(valor);
        dato.setTimestamp(LocalDateTime.now());
        dato.setSesion(sesionActiva);

        datoRepository.save(dato);
    }

    public void finalizarSesion(){
        if (sesionActiva != null) {
            sesionActiva.setFin(LocalDateTime.now());
            sesionRepository.save(sesionActiva);
            sesionActiva = null;
        }
    }

    public List<SesionHistorialDTO> obtenerHistorial(Long idPaciente) {
        List<SesionECG> sesiones = sesionRepository.findAll();

        return sesiones.stream().map(sesion -> {
            String nombrePaciente = sesion.getPaciente().getPrimerNombre() + " " + sesion.getPaciente().getPrimerApellido();
            String nombreMedico = sesion.getMedico().getPrimerNombre() + " " + sesion.getMedico().getPrimerApellido();

            List<DatoRequest> datos = sesion.getDatos().stream()
                    .map(dato -> new DatoRequest(dato.getValor(), dato.getTimestamp()))
                    .toList();

            return new SesionHistorialDTO(
                    sesion.getId(),
                    sesion.getInicio(),
                    sesion.getFin(),
                    nombrePaciente,
                    nombreMedico,
                    datos
            );
        }).toList();
    }

    public SesionECG getSesionActiva() {
        return sesionActiva;
    }

    public long totalSesiones(){
        return sesionRepository.count();
    }
}
