package com.ib.asistencia.util;

import java.util.Map;

public class ObservacionDia {

    private String observacion;
    private Long cantidad;

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
}
