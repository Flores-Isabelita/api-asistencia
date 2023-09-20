package com.ib.asistencia.util;

import com.ib.asistencia.domain.Persona;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
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
        filaEncabezados.createCell(0).setCellValue("ID");
        filaEncabezados.createCell(1).setCellValue("TURNO");
        filaEncabezados.createCell(2).setCellValue("NOMBRE");
        filaEncabezados.createCell(3).setCellValue("CEDULA");
        filaEncabezados.createCell(4).setCellValue("CELULAR");
            filaEncabezados.createCell(5).setCellValue("PROCESO");
            filaEncabezados.createCell(6).setCellValue("LABOR");
            filaEncabezados.createCell(7).setCellValue("OBSERVACION");

            // Aplicar formato a los encabezados
            CellStyle estiloEncabezados = libro.createCellStyle();
            Font fuente = libro.createFont();
            fuente.setBold(true);
            estiloEncabezados.setFont(fuente);
            estiloEncabezados.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            estiloEncabezados.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            estiloEncabezados.setBorderBottom(BorderStyle.THIN);
            estiloEncabezados.setBorderTop(BorderStyle.THIN);
            estiloEncabezados.setBorderLeft(BorderStyle.THIN);
            estiloEncabezados.setBorderRight(BorderStyle.THIN);
            for (Cell celda : filaEncabezados) {
                celda.setCellStyle(estiloEncabezados);
            }

            // Escribir los datos de las personas en las filas
            int filaActual = 1;
            CellStyle estiloCeldas = libro.createCellStyle();
            estiloCeldas.setBorderBottom(BorderStyle.THIN);
            estiloCeldas.setBorderTop(BorderStyle.THIN);
            estiloCeldas.setBorderLeft(BorderStyle.THIN);
            estiloCeldas.setBorderRight(BorderStyle.THIN);
            for (Map<String, Object> persona : personasConAsistenciaFiltrada) {
                XSSFRow filaPersona = hoja.createRow(filaActual++);
            filaPersona.createCell(0).setCellValue(persona.get("idEmpresa").toString());
            filaPersona.createCell(1).setCellValue(persona.get("turno").toString());
            filaPersona.createCell(2).setCellValue(persona.get("nombre").toString());
            filaPersona.createCell(3).setCellValue(persona.get("cedula").toString());
            filaPersona.createCell(4).setCellValue(persona.get("celular").toString());
            filaPersona.createCell(5).setCellValue(persona.get("proceso").toString());
            filaPersona.createCell(6).setCellValue(persona.get("labor").toString());
            filaPersona.createCell(7).setCellValue(persona.get("observacion").toString());

            // Aplicar formato a las celdas de datos
            for (Cell celda : filaPersona) {
                celda.setCellStyle(estiloCeldas);
            }
        }

        // Ajustar el ancho de las columnas
        hoja.autoSizeColumn(0);
        hoja.autoSizeColumn(1);
        hoja.autoSizeColumn(2);
        hoja.autoSizeColumn(3);
        hoja.autoSizeColumn(4);
        hoja.autoSizeColumn(5);
        hoja.autoSizeColumn(6);
        hoja.autoSizeColumn(7);

        // Escribir los datos en un arreglo de bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libro.write(outputStream);
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();

        return bytes;
    }

}