package com.ib.asistencia.util;

import com.ib.asistencia.domain.Persona;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CrearExcelPoi {

    //public static void main(String[] args) throws IOException {
    public static byte[] generarArchivoExcel(List<Map<String, Object>> personasConAsistenciaFiltrada) throws IOException {
        // Crear un nuevo libro de Excel
        XSSFWorkbook libro = new XSSFWorkbook();

        // Crear una nueva hoja de Excel
        XSSFSheet hoja = libro.createSheet("Personas con asistencia filtrada");

        // Crear una fila para los encabezados
        XSSFRow filaEncabezados = hoja.createRow(0);
        filaEncabezados.createCell(0).setCellValue("idEmpresa");
        filaEncabezados.createCell(1).setCellValue("nombre");
        filaEncabezados.createCell(2).setCellValue("cedula");
        filaEncabezados.createCell(3).setCellValue("celular");
        filaEncabezados.createCell(4).setCellValue("proceso");
        filaEncabezados.createCell(5).setCellValue("labor");
        filaEncabezados.createCell(6).setCellValue("observacion");

        // Escribir los datos de las personas en las filas
        int filaActual = 1;
        for (Map<String, Object> persona : personasConAsistenciaFiltrada) {
            XSSFRow filaPersona = hoja.createRow(filaActual++);
            filaPersona.createCell(0).setCellValue(persona.get("idEmpresa").toString());
            filaPersona.createCell(1).setCellValue(persona.get("nombre").toString());
            filaPersona.createCell(2).setCellValue(persona.get("cedula").toString());
            filaPersona.createCell(3).setCellValue(persona.get("celular").toString());
            filaPersona.createCell(4).setCellValue(persona.get("proceso").toString());
            filaPersona.createCell(5).setCellValue(persona.get("labor").toString());
            filaPersona.createCell(6).setCellValue(persona.get("observacion").toString());
        }

        // Escribir los datos en un arreglo de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libro.write(outputStream);
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();

        return bytes;
    }

}