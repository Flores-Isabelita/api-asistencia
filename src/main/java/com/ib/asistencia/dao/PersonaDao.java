package com.ib.asistencia.dao;

import com.ib.asistencia.domain.Persona;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaDao extends JpaRepository<Persona, Long>{

    @Query("SELECT p FROM Persona p WHERE"
            + " CONCAT(p.idEmpresa, p.nombre, p.cedula, p.proceso)"
            + " LIKE %?1% ORDER BY p.nombre")
    public List<Persona>  findAllByOrderByNombreAsc(String palabraClave);

    @Query("SELECT DISTINCT p.proceso FROM Persona p")
    List<String> findDistinctProcesos();

 /*   @Query("SELECT p.proceso, p.labor, count(p) " +
            "FROM Persona p " +
            "GROUP BY p.proceso, p.labor")
    List<Object[]> countPeopleByProcesoAndLabor();*/

    @Query("SELECT p.proceso, p.labor, count(p) " +
            "FROM Persona p " +
            "WHERE p.fechaActualizacion = (SELECT MAX(p2.fechaActualizacion) FROM Persona p2) " +
            "GROUP BY p.proceso, p.labor")
    List<Object[]> countPeopleByProcesoAndLaborWithMostRecentUpdate();


    @Query("SELECT p FROM Persona p WHERE p.fechaActualizacion = (SELECT MAX(p2.fechaActualizacion) FROM Persona p2) ORDER BY p.nombre")
    public List<Persona> findAllByFechaActualizacionMasReciente();

    public List<Persona> findAllByOrderByNombreAsc();

    public List<Persona> findAllByAsistenciasFechaAndAsistenciasEstado(String fecha, String estado);

}
