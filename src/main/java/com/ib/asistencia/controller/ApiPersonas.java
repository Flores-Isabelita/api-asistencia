package com.ib.asistencia.controller;


import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.servicio.PersonaService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/personas")
public class ApiPersonas {

    @Autowired
    private PersonaService personaService;

    @GetMapping("")
    public ArrayList<Persona> listarPersonas(String palabraClave){
        return (ArrayList<Persona>) personaService.listarPersonas(palabraClave);
    }

    @PutMapping("{idPersona}")
    Persona upDatePerson(@PathVariable String idPersona, @RequestBody @NotNull Persona persona){
        Persona personaFrontDb = personaService.encontrarPersona(idPersona);
        //personaFrontDb.setObservaciones(persona.getObservaciones());
        return personaService.guardar(personaFrontDb);
    }

    @PostMapping("")
    public Persona guardarPersona(@RequestBody Persona persona){
        return personaService.guardar(persona);
    }

}
