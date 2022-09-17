package com.ib.asistencia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ib.asistencia.domain.Usuario;
import com.ib.asistencia.servicio.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {


    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/usuarios")
    public String usuarios(Usuario usuario, Model model, @AuthenticationPrincipal User user){
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
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
