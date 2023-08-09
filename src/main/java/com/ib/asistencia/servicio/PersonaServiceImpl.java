package com.ib.asistencia.servicio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.ib.asistencia.dao.PersonaDao;
import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.util.ActualLaborProceso;
import com.ib.asistencia.util.PersonasAusentes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas(String palabraClave) {
        if (palabraClave != null) {
            return personaDao.findAllByOrderByNombreAsc(palabraClave);
        }

        return (List<Persona>) personaDao.findAllByFechaActualizacionMasReciente();
    }

    @Override
    public List listarProcesos() {
        return personaDao.findDistinctProcesos();
    }

    @Override
    @Transactional
    public Persona guardar(Persona persona) {

        return personaDao.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(String id) {
        return personaDao.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<ActualLaborProceso> listarExixtenciaPorProceso() {
        List<Object[]> result = personaDao.countPeopleByProcesoAndLaborWithMostRecentUpdate();
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (Object[] row : result) {
            String proceso = (String) row[0];
            String labor = (String) row[1];
            int count = ((Number) row[2]).intValue();
            if (!map.containsKey(proceso)) {
                map.put(proceso, new HashMap<>());
            }
            map.get(proceso).put(labor, count);
        }
        List<ActualLaborProceso> procesoLabores = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            procesoLabores.add(new ActualLaborProceso(entry.getKey(), entry.getValue()));
        }
        return procesoLabores;
    }

    @Override
    public List<Map<String, Object>> listarPersonasAusentesHoy() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fechaActual = dateFormat.format(date);
        List<Persona> personasAusentes = personaDao.findAllByAsistenciasFechaAndAsistenciasEstado(fechaActual, "0");
        List<Map<String, Object>> personasConAsistenciaFiltrada = new ArrayList<Map<String, Object>>();
        for (Persona persona : personasAusentes) {
            Map<String, Object> personaFiltrada = new HashMap<>();
            personaFiltrada.put("idEmpresa", persona.getIdEmpresa());
            personaFiltrada.put("nombre", persona.getNombre());
            personaFiltrada.put("cedula", persona.getCedula());
            personaFiltrada.put("celular", persona.getCelular());
            personaFiltrada.put("proceso", persona.getProceso());
            personaFiltrada.put("labor", persona.getLabor());
            personaFiltrada.put("observacion", persona.getUltimaAsistencia().getObservacion());
            personasConAsistenciaFiltrada.add(personaFiltrada);
        }

        return personasConAsistenciaFiltrada;
    }

}
