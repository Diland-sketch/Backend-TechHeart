package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.dto.LoginResponse;
import com.kadTI.techHeart_backend.dto.PacienteDTO;
import com.kadTI.techHeart_backend.dto.RolDTO;
import com.kadTI.techHeart_backend.dto.UsuarioDTO;
import com.kadTI.techHeart_backend.entity.Paciente;
import com.kadTI.techHeart_backend.entity.Rol;
import com.kadTI.techHeart_backend.entity.Usuario;
import com.kadTI.techHeart_backend.repository.PacienteRepository;
import com.kadTI.techHeart_backend.service.PacienteService;
import com.kadTI.techHeart_backend.service.RolService;
import com.kadTI.techHeart_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private RolService rolService;



    @GetMapping("/ver")
    public ResponseEntity<List<Paciente>> Mostrar() {
        List<Paciente> pacientes;
        pacientes = pacienteService.verTodos();
        return ResponseEntity.ok(pacientes);

    }

    @PostMapping("/crear")
    public ResponseEntity<Paciente> Guardar(@RequestBody Paciente paciente) {

        if(paciente.getRol() != null && paciente.getRol().getIdRol() != null){
            Rol rol = rolService.buscarPorId(paciente.getRol().getIdRol());
            if (rol == null){
                return ResponseEntity.badRequest().body(null);
            }
            paciente.setRol(rol);
        }

        pacienteService.CrearPaciente(paciente);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> pacientePorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.BuscarPaciente(id));
    } 

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Paciente> Modificar(@PathVariable Long id, @RequestBody Paciente paciente){
        Paciente pacienteModificado = pacienteService.BuscarPaciente(id);
        if (pacienteModificado != null){

            pacienteModificado.setPrimerNombre(paciente.getPrimerNombre());
            pacienteModificado.setSegundoNombre(paciente.getSegundoNombre());
            pacienteModificado.setPrimerApellido(paciente.getPrimerApellido());
            pacienteModificado.setSegundoApellido(paciente.getSegundoApellido());
            pacienteModificado.setEmail(paciente.getEmail());
            pacienteModificado.setNombreUsuario(paciente.getNombreUsuario());
            pacienteModificado.setTipoSangre(paciente.getTipoSangre());
            pacienteModificado.setCondicionesMedicas(paciente.getCondicionesMedicas());
            pacienteModificado.setEdad(paciente.getEdad());
            pacienteModificado.setTelefono(paciente.getTelefono());




            return ResponseEntity.ok(pacienteService.Modificar(pacienteModificado));
        }else{
            return ResponseEntity.ok(paciente);
        }

    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> Eliminar(@PathVariable Long id){

        Paciente paciente = pacienteService.BuscarPaciente(id);

        pacienteService.Eliminar(paciente);

        return ResponseEntity.ok("Eliminado con exito");
    }

    @GetMapping("/total")
    public long contarPacientes(){
        return pacienteService.contarPacientes();
    }

}
