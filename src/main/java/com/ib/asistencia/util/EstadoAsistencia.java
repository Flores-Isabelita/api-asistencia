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
        if (fecha != null) {
            asistencias = asistenciaService.listarAsistencia(fecha);
        } else {
            asistencias = asistenciaService.listarAsistencia("");
        }
        procesos = personaService.listarProcesos();
    }

    public ArrayList<AsistenciaProceso> obtener() {
        HashMap<String, AsistenciaProceso> estadoProcesos = new HashMap<>();

        for (String proceso : procesos) {
            AsistenciaProceso obj = new AsistenciaProceso(proceso, 0, 0, 0, new HashMap<>());
            estadoProcesos.put(proceso, obj);
        }

        for (Asistencia asistencia : asistencias) {
            var proceso = asistencia.getPersona().getProceso();
            var estado = asistencia.getEstado();
            var labor = asistencia.getPersona().getLabor();

            for (AsistenciaProceso value : estadoProcesos.values()) {
                if (proceso.equals(value.nombre)) {
                    if (estado.equals("1")) {
                        value.presentes = value.presentes + 1;
                        if (value.getLabor().containsKey(labor)) {
                            value.getLabor().put(labor, value.getLabor().get(labor) + 1);
                        } else {
                            value.getLabor().put(labor, 1);
                        }
                    } else {
                        value.ausentes = value.ausentes + 1;
                    }
                }
            }
        }

        // Obtener la lista de personas por proceso
        List<ActualLaborProceso> personasPorProceso = personaService.listarExixtenciaPorProceso();

        // Calcular la cantidad de personas pendientes por validar
        for (ActualLaborProceso personaProceso : personasPorProceso) {
            String proceso = personaProceso.getProceso();
            int presentes = 0;
            int ausentes = 0;

            for (AsistenciaProceso value : estadoProcesos.values()) {
                if (proceso.equals(value.nombre)) {
                    presentes = value.presentes;
                    ausentes = value.ausentes;
                    break;
                }
            }

            int total = 0;
            for (Map.Entry<String, Integer> laborEntry : personaProceso.getLabor().entrySet()) {
                total += laborEntry.getValue();
            }

            int pendientes = total - (presentes + ausentes);

            for (AsistenciaProceso value : estadoProcesos.values()) {
                if (proceso.equals(value.nombre)) {
                    value.pendientes = pendientes;
                    break;
                }
            }
        }

        ArrayList<AsistenciaProceso> listEstadoAsistenciaProcesos = new ArrayList<>(estadoProcesos.values());
        return listEstadoAsistenciaProcesos;
    }
}
