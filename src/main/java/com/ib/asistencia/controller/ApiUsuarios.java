package com.ib.asistencia.controller;

import com.ib.asistencia.domain.Usuario;
import com.ib.asistencia.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/usuarios")
public class ApiUsuarios {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public List<Usuario> usuarios(Usuario usuario){
        var usuarios = usuarioService.listarUsuarios();
        return usuarios;
    }
    
    @PostMapping("")
    public Usuario guardar(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }

    @PutMapping("{idUsuario}")
    Usuario upDatePerson(@PathVariable String idUsuario, @RequestBody @NotNull Usuario usuario){
        Usuario usuarioFrontDb = usuarioService.encontrarUsuario(idUsuario);
        return usuarioService.guardar(usuarioFrontDb);
    }

    @PostMapping("/eliminar")
    public void eliminar(@RequestBody Usuario usuario){
        usuarioService.eliminar(usuario);
    }

}
