package com.ib.asistencia.servicio;

import java.util.List;
import java.util.Objects;

import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.util.ActualLaborProceso;
import com.ib.asistencia.util.AsistenciaProceso;

public interface PersonaService {
    
    public List<Persona> listarPersonas(String palabraClave);

    public List listarProcesos();
    
    public Persona guardar(Persona persona);
 
    public Persona encontrarPersona(String id);

    public List<ActualLaborProceso> listarExixtenciaPorProceso();

}
