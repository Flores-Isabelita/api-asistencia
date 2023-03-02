package com.ib.asistencia.controller;


import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.servicio.PersonaService;
import com.ib.asistencia.util.ActualLaborProceso;
import com.ib.asistencia.util.CrearExcelPoi;
import com.ib.asistencia.util.LeerExcelPoi;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/file")
public class ApiFie {

    @Autowired
    private PersonaService personaService;
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

    @GetMapping("/donwload")
    public ResponseEntity<byte[]> donwloadFile() throws IOException {

        CrearExcelPoi crearExcel = new CrearExcelPoi();
        // Obtener la lista de personas con asistencia filtrada
        List<Map<String, Object>> personasConAsistenciaFiltrada = personaService.listarPersonasAusentesHoy();

        // Generar el archivo de Excel
        byte[] bytes = crearExcel.generarArchivoExcel(personasConAsistenciaFiltrada);

        // Configurar la respuesta para descargar el archivo de Excel
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.attachment().filename("personas-con-asistencia-filtrada.xlsx").build());

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
