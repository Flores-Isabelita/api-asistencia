package com.ib.asistencia.controller;


import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.servicio.PersonaService;
import com.ib.asistencia.util.ActualLaborProceso;
import com.ib.asistencia.util.LeerExcelPoi;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/personas")
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

    @PostMapping("/upload")
    public List uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes)
            throws IOException {
        if (file == null || file.isEmpty()) {
            attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
            return personaService.listarPersonas(null);
        }

        StringBuilder builder = new StringBuilder();

        byte[] fileBytes = file.getBytes();
        Path path = Paths.get(System.getProperty("java.io.tmpdir") + File.separator + file.getOriginalFilename());

        Files.write(path, fileBytes);

        attributes.addFlashAttribute("rutaArchivo", "[" + builder.toString() + "]");

        LeerExcelPoi leerExcel = new LeerExcelPoi();

        var personas = leerExcel.listar(String.valueOf(path));

        for (var p : personas) {
            personaService.guardar((Persona)p);
        }

        return personaService.listarPersonas(null);
    }


    @PostMapping("")
    public Persona guardarPersona(@RequestBody Persona persona){
        return personaService.guardar(persona);
    }

    @GetMapping("/total")
    public List<ActualLaborProceso> listarTotalPersonas(){
        return personaService.listarExixtenciaPorProceso();
    }

}
