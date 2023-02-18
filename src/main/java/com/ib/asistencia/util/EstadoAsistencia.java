package com.ib.asistencia.util;

import com.ib.asistencia.domain.Asistencia;
import com.ib.asistencia.servicio.AsistenciaService;
import com.ib.asistencia.servicio.PersonaService;

import java.util.*;

public class EstadoAsistencia {

    private final AsistenciaService asistenciaService;

    private final PersonaService personaService;

    List<String> procesos;
    List<Asistencia> asistencias;

    public EstadoAsistencia(AsistenciaService asistenciaService, PersonaService personaService, String fecha) {
        this.asistenciaService = asistenciaService;
        this.personaService = personaService;
        if (fecha != null){
            asistencias = asistenciaService.listarAsistencia(fecha);
        }else {
            asistencias = asistenciaService.listarAsistencia("");
        }
        procesos = personaService.listarProcesos();
    }

    public ArrayList<AsistenciaProceso> obtener() {

        HashMap<String, AsistenciaProceso> estadoProcesos = new HashMap<>();

        for (String proceso : procesos) {
            AsistenciaProceso obj = new AsistenciaProceso(proceso, 0, 0, new HashMap<>());
            estadoProcesos.put(proceso, obj);
        }

        for (Asistencia asistencia: asistencias)
        {
            var proceso = asistencia.getPersona().getProceso();
            var estado = asistencia.getEstado();
            var labor = asistencia.getPersona().getLabor();

            for (AsistenciaProceso value: estadoProcesos.values()
            ) {
                if (proceso.equals(value.nombre)){
                    if (estado.equals("1")){
                        AsistenciaProceso obj = estadoProcesos.get(value.nombre);
                        obj.presentes = (value.presentes) + 1;
                        obj.ausentes = value.ausentes;
                        if (obj.getLabor().containsKey(labor)) {
                            obj.getLabor().put(labor, obj.getLabor().get(labor) + 1);
                        } else {
                            obj.getLabor().put(labor, 1);
                        }
                        estadoProcesos.put(value.nombre, obj);
                    }else {
                        AsistenciaProceso obj = estadoProcesos.get(value.nombre);
                        obj.presentes = value.presentes;
                        obj.ausentes = (value.ausentes) + 1;
                        estadoProcesos.put(value.nombre, obj);
                    }
                }
            }
        }

        ArrayList<AsistenciaProceso> listEstadoAsistenciaProcesos = new ArrayList<>(estadoProcesos.values());

        return listEstadoAsistenciaProcesos;
    }
}

