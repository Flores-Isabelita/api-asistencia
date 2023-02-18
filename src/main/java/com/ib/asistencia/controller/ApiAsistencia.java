package com.ib.asistencia.controller;

import com.ib.asistencia.domain.Asistencia;
import com.ib.asistencia.servicio.AsistenciaService;
import com.ib.asistencia.servicio.PersonaService;
import com.ib.asistencia.util.EstadoAsistencia;
import com.ib.asistencia.util.AsistenciaProceso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/asistencia")
public class ApiAsistencia {

    @Autowired
    private AsistenciaService asistenciaService;
    @Autowired
    private PersonaService personaService;

    @GetMapping("")
    public ArrayList<Asistencia> listarAsistencia(@RequestParam(value = "fecha", required = false) String fecha){
        return (ArrayList<Asistencia>) asistenciaService.listarAsistencia(fecha);
    }

    @GetMapping("/procesos")
    public ArrayList<AsistenciaProceso> estadoProcesos(@RequestParam(value = "fecha", required = false) String fecha){
        EstadoAsistencia estado = new EstadoAsistencia(asistenciaService, personaService, fecha);
        return estado.obtener();
    }

    @PostMapping("")
    public Asistencia guardarAsistencia(@RequestBody Asistencia asistencia){
        Asistencia asistenciaExistente = asistenciaService.buscarPorIdEmpresaAndFecha(asistencia.getPersona(), asistencia.getFecha());
        if (asistenciaExistente != null) {
            asistencia.setIdAsistencia(asistenciaExistente.getIdAsistencia());
            return asistenciaService.guardar(asistencia);
        }
        return asistenciaService.guardar(asistencia);
    }

}
