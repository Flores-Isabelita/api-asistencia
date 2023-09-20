package com.ib.asistencia.dao;

import com.ib.asistencia.domain.Asistencia;
import com.ib.asistencia.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaDao extends JpaRepository<Asistencia, Long> {

    public List<Asistencia> findAllByFecha(String fecha);

    Asistencia findByPersonaAndFecha(Persona persona, String fecha);

    @Query("SELECT a.observacion, COUNT(a.observacion) FROM Asistencia a WHERE a.fecha = CURRENT_DATE GROUP BY a.observacion")
    List<Object[]> findObservacionesDia();
}
