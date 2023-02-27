package com.ib.asistencia.servicio;

import com.ib.asistencia.domain.Asistencia;
import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.util.ObservacionDia;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public interface AsistenciaService {

    public List<Asistencia> listarAsistencia(String fecha);

    public Asistencia guardar(Asistencia asistencia);

    public Asistencia buscarPorIdEmpresaAndFecha(Persona persona, String fecha);

    public List<ObservacionDia> ObservacionesDia();

}
