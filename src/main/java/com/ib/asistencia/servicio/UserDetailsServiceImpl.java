package com.ib.asistencia.servicio;

import com.ib.asistencia.dao.UsuarioDao;
import com.ib.asistencia.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Usuario usuario = usuarioDao
               .findOneByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no exixte"));
                return new UserDetailsImpl(usuario);
    }


    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    @Transactional
    public Usuario encontrarUsuario(String id) {
        return usuarioDao.findByNombre(id);
    }

    @Override
    @Transactional
    public void eliminar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
