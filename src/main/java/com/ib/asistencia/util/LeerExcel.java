package com.ib.asistencia.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ib.asistencia.domain.Persona;

import jxl.Sheet;
import jxl.Workbook;

public class LeerExcel {

    public List listar(String ruta) {

        String idEmpresa = "";
        String nombre = "";
        String cedula = "";
        String celular = "";
        String proceso = "";
        String labor = "";

        List<Persona> Personas = new ArrayList<Persona>();
        // String ruta2 = "C:\\Users\\daniel.guzman\\spring_upload_example\\Hoja.xlsx";

        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(ruta));

            for (int hojas = 0; hojas < archivoExcel.getNumberOfSheets(); hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int numColum = hoja.getColumns();
                int numfil = hoja.getRows();
                String dato;

                for (int fila = 1; fila < numfil; fila++) {

                    int contador = 1;

                    for (int col = 0; col < numColum; col++) {
                        dato = hoja.getCell(col, fila).getContents();

                        switch (contador) {
                            case 1:
                                idEmpresa = dato;
                                contador++;
                                break;
                            case 8:
                                nombre = dato;
                                contador++;
                                break;
                            case 10:
                                cedula = dato;
                                contador++;
                                break;
                            case 26:
                                celular = dato;
                                contador++;
                                break;
                            case 43:
                                proceso = dato;
                                contador++;
                                break;
                            case 4:
                                labor = dato;
                                contador++;
                                break;
                            default:
                                contador++;
                                break;
                        }

                    }

                    Persona persona = new Persona();
                    persona.setIdEmpresa(Long.valueOf(idEmpresa));
                    persona.setNombre(nombre);
                    persona.setCedula(cedula);
                    persona.setCelular(celular);
                    persona.setProceso(proceso);
                    persona.setLabor(labor);

                    Personas.add(persona);

                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return Personas;

    }

}
