package com.ib.asistencia.dao;

import com.ib.asistencia.domain.Asistencia;
import com.ib.asistencia.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaDao extends JpaRepository<Asistencia, Long> {

    @Query("SELECT p FROM Asistencia p WHERE"
            + " CONCAT(p.idAsistencia, p.fecha, p.estado)"
            + " LIKE %?1%")
    public List<Asistencia> findAll(String palabraClave);

    Asistencia findByPersonaAndFecha(Persona persona, String fecha);
}
