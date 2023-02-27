package com.ib.asistencia.dao;

import com.ib.asistencia.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    public Usuario findByNombre(String username);

    Optional<Usuario> findOneByEmail(String email);
}
