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
import com.kadTI.techHeart_backend.websocket.ECGWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final List<Long> picosDetectados = new ArrayList<>();
    private static final int UMBRAL_PICO = 600;
    private static final long MIN_INTERVALO_MS = 300;


    public SesionECG iniciarSesion(Long idPaciente, String usernameMedico){
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Medico medico = medicoRepository.findByNombreUsuario(usernameMedico)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        SesionECG sesion = new SesionECG();
        sesion.setInicio(LocalDateTime.now());
        sesion.setPaciente(paciente);
        sesion.setMedico(medico);

        return sesionRepository.save(sesion);
    }

    @Autowired
    ECGWebSocketHandler ecgWebSocketHandler;

    public void guardarDatoEnSesion(Long idSesion, int valor) {
        System.out.println("LLEGÓ DATO: " + valor + " para sesión: " + idSesion);
        SesionECG sesion = sesionRepository.findById(idSesion)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));

        DatoECG dato = new DatoECG();
        dato.setValor(valor);
        dato.setTimestamp(LocalDateTime.now());
        dato.setSesion(sesion);
        datoRepository.save(dato);

        long timestampActual = System.currentTimeMillis();
        Integer bpmCalculado = null;

        if(valor > UMBRAL_PICO){
            if(picosDetectados.isEmpty() || (timestampActual - picosDetectados.get(picosDetectados.size() - 1)) > MIN_INTERVALO_MS){
                picosDetectados.add(timestampActual);

                if(picosDetectados.size()>=2){
                    long intervalo = timestampActual - picosDetectados.get(picosDetectados.size() - 2);
                    bpmCalculado = (int)(60000 / intervalo);
                }
            }
        }

        String json;
        if (bpmCalculado != null){
             json = String.format("{\"valor\": %d, \"bpm\": %d }", valor, bpmCalculado);
        }else{
            json = String.format("{\"valor\": %d }", valor);
        }
        try{
            ecgWebSocketHandler.enviarDatoTiempoReal(json);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar dato por WebSocket: " + e.getMessage());
        }
    }

    public void finalizarSesion(Long idSesion) {
        SesionECG sesion = sesionRepository.findById(idSesion)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));

        try{
            String ipESP32 = "192.168.12.13";
            String url = "http://" + ipESP32 + "/detener-sesion";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.noBody()) // sin body
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("ESP32 respondió OK: " + response.body());
            } else {
                System.err.println("ESP32 respondió con error: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Error al detener sesión en ESP32: " + e.getMessage());
            // Aquí puedes decidir si lanzar excepción o continuar
        }

        sesion.setFin(LocalDateTime.now());
        sesionRepository.save(sesion);
    }

    public SesionECG BuscarSesion(Long id){

        Optional<SesionECG> OpcionalSesion   =  sesionRepository.findById(id);
        if(OpcionalSesion.isPresent()){
            SesionECG sesionECG = OpcionalSesion.get();
            return sesionECG;
        }
        return null;
    }

    public List<SesionHistorialDTO> obtenerHistorial(Long idPaciente) {
        List<SesionECG> sesiones = sesionRepository.obtenerSesionesPorPaciente(idPaciente);

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

    public long totalSesiones(){
        return sesionRepository.count();
    }
}
