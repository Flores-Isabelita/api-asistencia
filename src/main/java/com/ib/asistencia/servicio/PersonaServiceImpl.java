package com.ib.asistencia.servicio;

import java.util.List;
import com.ib.asistencia.dao.PersonaDao;
import com.ib.asistencia.domain.Persona;
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
            return personaDao.findAll(palabraClave);
        }
        return (List<Persona>) personaDao.findAll();
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
}
