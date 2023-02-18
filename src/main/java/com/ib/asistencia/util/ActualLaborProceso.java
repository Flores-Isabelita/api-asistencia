package com.ib.asistencia.util;

import java.util.Map;

public class ActualLaborProceso {

    private String proceso;
    private Map<String, Integer> labor;

    public ActualLaborProceso(String proceso, Map<String, Integer> labor) {
        this.proceso = proceso;
        this.labor = labor;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Map<String, Integer> getLabor() {
        return labor;
    }

    public void setLabor(Map<String, Integer> labor) {
        this.labor = labor;
    }
}
