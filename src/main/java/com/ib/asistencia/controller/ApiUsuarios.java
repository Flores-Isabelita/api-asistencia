package com.ib.asistencia.controller;

import com.ib.asistencia.domain.Usuario;
import com.ib.asistencia.servicio.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ApiUsuarios {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("")
    public List<Usuario> usuarios(Usuario usuario){
        var usuarios = usuarioService.listarUsuarios();
        return usuarios;
    }

    @PostMapping("/agregar")
    public String guardar(@Valid Usuario usuario, Errors errores){
        if(errores.hasErrors()){
            return "usuarios";
        }
        usuarioService.agregar(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar")
    public String eliminar(Usuario usuario){
        usuarioService.eliminar(usuario);
        return "redirect:/usuarios";
    }




    
}
