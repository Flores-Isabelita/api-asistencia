package com.ib.asistencia.util;

import com.ib.asistencia.dao.UsuarioDao;
import com.ib.asistencia.domain.Rol;
import com.ib.asistencia.domain.Usuario;
import com.ib.asistencia.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {

        List<Rol> roles = new ArrayList<>(); // Crear una lista vacía
        Rol admin = new Rol("ADMIN"); // Crear un objeto de la clase Rol con el nombre "ADMIN"
        roles.add(admin);
        Usuario usuario = new Usuario();
        usuario.setEmail("admin");
        usuario.setPassword("admin");
        usuario.setNombre("admin");
        usuario.setRoles(roles);

        // Agrega aquí el código para cargar los datos iniciales en la base de datos
        usuarioService.guardar(usuario);



    }
}

