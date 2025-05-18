package com.kadTI.techHeart_backend.controller;

import com.kadTI.techHeart_backend.dto.MedicoDTO;
import com.kadTI.techHeart_backend.dto.PacienteDTO;
import com.kadTI.techHeart_backend.entity.Medico;
import com.kadTI.techHeart_backend.entity.Paciente;
import com.kadTI.techHeart_backend.entity.Rol;
import com.kadTI.techHeart_backend.service.MedicoService;
import com.kadTI.techHeart_backend.service.PacienteService;
import com.kadTI.techHeart_backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private RolService rolService;

    @GetMapping("/ver")
    public ResponseEntity<List<Medico>> Mostrar() {
        List<Medico> medico;
        medico = medicoService.verTodos();
        return ResponseEntity.ok(medico);

    }

    @PostMapping("/crear")
    public ResponseEntity<Medico> Guardar(@RequestBody Medico medico) {

        if(medico.getRol() != null && medico.getRol().getIdRol() != null){
            Rol rol = rolService.buscarPorId(medico.getRol().getIdRol());
            if (rol == null){
                return ResponseEntity.badRequest().body(null);
            }
            medico.setRol(rol);
        }

        medicoService.CrearMedico(medico);
        return ResponseEntity.ok(medico);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> Modificar(@PathVariable Long id, @RequestBody Medico medico){
        Medico medicoModificado = medicoService.BuscarMedico(id);
        if (medicoModificado != null){

            medicoModificado.setPrimerNombre(medico.getPrimerNombre());
            medicoModificado.setSegundoNombre(medico.getSegundoNombre());
            medicoModificado.setPrimerApellido(medico.getPrimerApellido());
            medicoModificado.setSegundoApellido(medico.getSegundoApellido());
            medicoModificado.setEmail(medico.getEmail());
            medicoModificado.setNombreUsuario(medico.getNombreUsuario());
            medicoModificado.setEspecialidad(medico.getEspecialidad());
            medicoModificado.setFechaGraduacion(medico.getFechaGraduacion());
            medicoModificado.setEdad(medico.getEdad());
            medicoModificado.setSexo(medico.getSexo());
            medicoModificado.setLugarGraduacion(medico.getLugarGraduacion());

            medicoService.Modificar(medicoModificado);


            return ResponseEntity.ok("modificado con exito");
        }else{
            return ResponseEntity.ok("Paciente no encontrado");
        }

    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> Eliminar(@PathVariable Long id){

        Medico medico = medicoService.BuscarMedico(id);

        medicoService.Eliminar(medico);

        return ResponseEntity.ok("Eliminado con exito");
    }

}
