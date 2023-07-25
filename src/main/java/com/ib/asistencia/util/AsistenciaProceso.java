package com.ib.asistencia.util;

import java.util.Map;

public class AsistenciaProceso {
    String nombre;
    int presentes;
    int ausentes;

    int pendientes;

    private Map<String, Integer> labor;


    public AsistenciaProceso(String nombre, int presentes, int ausentes, int pendientes, Map<String, Integer> labor) {
        this.nombre = nombre;
        this.presentes = presentes;
        this.ausentes = ausentes;
        this.labor = labor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPresentes() {
        return presentes;
    }

    public void setPresentes(int presentes) {
        this.presentes = presentes;
    }

    public int getAusentes() {
        return ausentes;
    }

    public void setAusentes(int ausentes) {
        this.ausentes = ausentes;
    }

    public int getPendientes() {
        return pendientes;
    }

    public void setPendientes(int pendientes) {
        this.pendientes = pendientes;
    }

    public Map<String, Integer> getLabor() {
        return labor;
    }

    public void setLabor(Map<String, Integer> labor) {
        this.labor = labor;
    }
}
