package com.ib.asistencia.servicio;

import com.ib.asistencia.dao.AsistenciaDao;
import com.ib.asistencia.domain.Asistencia;
import com.ib.asistencia.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class AsistenciaServiceImpl implements AsistenciaService{


    @Autowired
    private AsistenciaDao asistenciaDao;

    @Override
    public List<Asistencia> listarAsistencia(String fecha) {
        System.out.println(fecha);
        return (List<Asistencia>) asistenciaDao.findAll(fecha);
    }

    @Override
    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaDao.save(asistencia);
    }

    @Override
    public Asistencia buscarPorIdEmpresaAndFecha(Persona persona, String fecha) {
        return asistenciaDao.findByPersonaAndFecha(persona, fecha);
    }



}
