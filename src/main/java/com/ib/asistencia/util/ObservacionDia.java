package com.ib.asistencia.util;

import java.util.Map;

public class ObservacionDia {

    private String observacion;
    private Long cantidad;

    private String turno;

    public ObservacionDia() {
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }


}
