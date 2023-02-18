package com.ib.asistencia.servicio;

import java.util.List;

import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.domain.Usuario;

public interface UsuarioService {


    public List<Usuario> listarUsuarios();

    public Usuario encontrarUsuario(String id);

    public Usuario guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    
}
