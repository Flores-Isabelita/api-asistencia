package com.ib.asistencia.servicio;

import java.util.List;
import com.ib.asistencia.domain.Persona;

public interface PersonaService {
    
    public List<Persona> listarPersonas(String palabraClave);
    
    public Persona guardar(Persona persona);
 
    public Persona encontrarPersona(String id);

}
