package com.ib.asistencia.servicio;

import java.util.*;

import com.ib.asistencia.dao.PersonaDao;
import com.ib.asistencia.domain.Persona;
import com.ib.asistencia.util.ActualLaborProceso;
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
        return (List<Persona>) personaDao.findAll();
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
        List<Object[]> result = personaDao.countPeopleByProcesoAndLabor();
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

}
