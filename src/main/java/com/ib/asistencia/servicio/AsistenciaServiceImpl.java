package com.ib.asistencia.servicio;

import com.ib.asistencia.dao.AsistenciaDao;
import com.ib.asistencia.domain.Asistencia;
import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.util.ObservacionDia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenciaServiceImpl implements AsistenciaService{


    @Autowired
    private AsistenciaDao asistenciaDao;

    @Override
    public List<Asistencia> listarAsistencia(String fecha) {
        System.out.println(fecha);
        return (List<Asistencia>) asistenciaDao.findAllByFecha(fecha);
    }

    @Override
    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaDao.save(asistencia);
    }

    @Override
    public Asistencia buscarPorIdEmpresaAndFecha(Persona persona, String fecha) {
        return asistenciaDao.findByPersonaAndFecha(persona, fecha);
    }

    public List<ObservacionDia> ObservacionesDia() {
        List<Object[]> result = asistenciaDao.findObservacionesDia();
        List<ObservacionDia> observacionesDia = new ArrayList<>();
        for (Object[] obj : result) {
            ObservacionDia obs = new ObservacionDia();
            obs.setObservacion((String) obj[0]);
            obs.setCantidad((Long) obj[1]);
            observacionesDia.add(obs);
        }
        return observacionesDia;
    }


}
