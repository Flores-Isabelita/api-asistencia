package com.ib.asistencia.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.math3.util.Precision;

import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.servicio.PersonaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    
    @RequestMapping("/")
    public String inicio(Model model, @Param("palabraClave") String palabraClave, @AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas(palabraClave);
        var personasProcesos = personaService.listarPersonas("");
        List procesosList = new ArrayList(); 
        personas.sort(Comparator.comparing(Persona::getNombre));
        log.info("usuario que hizo login:" + user);
        for (Persona persona : personasProcesos) {
            procesosList.add(persona.getProceso());
        }
        HashSet hs = new HashSet(); 
        hs.addAll(procesosList); 
        procesosList.clear(); 
        procesosList.addAll(hs);
        Collections.sort(procesosList);

        

        model.addAttribute("personas", personas);
        model.addAttribute("totalPersonas", personas.size());
        model.addAttribute("procesos", procesosList);
        model.addAttribute("palabraClave", palabraClave);

        return "index";
    }



    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "redirect:/";
        }
        /*if ("Ausente".equals(persona.getEstado()) && persona.getObservaciones().isEmpty()) {
            persona.setObservaciones("Ausente");
        }
          */
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/tablero")
    public String tablero(Model model){
        var personas = personaService.listarPersonas("");
        var personasPresentes = 0;
        var personasAusentes = 0;
        var personasSinValidar = personas.size();
        for (Persona personaActual : personas) {
            /*
            if ("Presente".equals(personaActual.getEstado())) {
                personasPresentes++;
                personasSinValidar--;
            }
            if ("Ausente".equals(personaActual.getEstado())) {
                personasAusentes++;
                personasSinValidar--;
            }

             */
        }
        double  PorcentajeSinValidar = Double.valueOf((personasSinValidar) * 100) / Double.valueOf(personas.size());
        double  PorcentajeAusentes = Double.valueOf((personasAusentes) * 100) / Double.valueOf(personas.size());
        double  PorcentajePresentes = Double.valueOf((personasPresentes) * 100) / Double.valueOf(personas.size());

        model.addAttribute("personasPresentes", personasPresentes);
        model.addAttribute("personasAusentes", personasAusentes);
        model.addAttribute("personasSinValidar", personasSinValidar);
        model.addAttribute("totalPersonas", personas.size());
        model.addAttribute("PorcentajeSinValidar",  Precision.round(PorcentajeSinValidar,2));
        model.addAttribute("PorcentajeAusentes", Precision.round(PorcentajeAusentes,2));
        model.addAttribute("PorcentajePresentes", Precision.round(PorcentajePresentes,2));

        return "tablero";
    }

}
