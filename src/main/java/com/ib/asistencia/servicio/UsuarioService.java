package com.ib.asistencia.servicio;

import java.util.List;
import com.ib.asistencia.domain.Usuario;

public interface UsuarioService {


    public List<Usuario> listarUsuarios();

    public void agregar(Usuario usuario);

    public void eliminar(Usuario usuario);

    
}
