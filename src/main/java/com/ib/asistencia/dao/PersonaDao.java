package com.ib.asistencia.dao;

import com.ib.asistencia.domain.Persona;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaDao extends JpaRepository<Persona, Long>{

    @Query("SELECT p FROM Persona p WHERE"
    + " CONCAT(p.idEmpresa, p.nombre, p.cedula, p.proceso)"
    + " LIKE %?1%")
    public  List<Persona> findAll(String palabraClave);

}
