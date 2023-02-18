package com.ib.asistencia.security;

import com.ib.asistencia.dao.UsuarioDao;
import com.ib.asistencia.domain.Usuario;
import com.ib.asistencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioRepository;
   /* private UsuarioRepository usuarioRepository;*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Usuario usuario = usuarioRepository
               .findOneByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no exixte"));

                return new UserDetailsImpl(usuario);

    }


}
