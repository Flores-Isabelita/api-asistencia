package com.ib.asistencia.controller;


import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/personas")
public class ApiPersonas {

    @Autowired
    private PersonaService personaService;

    @GetMapping("")
    public ArrayList<Persona> listarPersonas(String palabraClave){
        return (ArrayList<Persona>) personaService.listarPersonas(palabraClave);
    }

    @PostMapping("")
    public Persona guardarPersona(@RequestBody Persona persona){
        return this.personaService.guardar(persona);
    }

}
