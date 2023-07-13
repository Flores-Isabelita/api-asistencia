package com.ib.asistencia.util;

import com.ib.asistencia.domain.Persona;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeerExcelPoi {

    //public static void main(String[] args) throws IOException {
        public List listar(String ruta) throws IOException {

        int idEmpresa = 0;
        String nombre = "";
        String cedula = "";
        String celular = "";
        String proceso = "";
        String labor = "";
        LocalDate fechaActual = LocalDate.now();
        String fechaEnFormato = fechaActual.toString();

        List<Persona> Personas = new ArrayList<Persona>();

        //Abrir fichero de Excel
        File f = new File(ruta);
        InputStream inp = new FileInputStream(f);
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
        sheet.getTopRow();

        //Recorrer el fichero de Excel
        var iRow = 1;
        Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
        while(row!=null)
        {
            int colum = 0;
            while (colum<50) {
                Cell cell = row.getCell(colum);
                String value = convertCellToString(cell);

                switch (colum) {
                    case 0:
                        idEmpresa = Integer.parseInt(value);
                        colum++;
                        break;
                    case 8:
                        nombre = value;
                        colum++;
                        break;
                    case 10:
                        cedula = value;
                        colum++;
                        break;
                    case 25:
                        celular = value;
                        colum++;
                        break;
                    case 43:
                        proceso = value;
                        colum++;
                        break;
                    case 4:
                        labor = value;
                        colum++;
                        break;
                    default:
                        colum++;
                        break;
                }
            }

            Persona persona = new Persona();
            persona.setIdEmpresa((long) idEmpresa);
            persona.setNombre(nombre);
            persona.setCedula(cedula);
            persona.setCelular(celular);
            persona.setProceso(proceso);
            persona.setLabor(labor);
            persona.setFechaActualizacion(fechaEnFormato);

            Personas.add(persona);
            iRow++;
            row = sheet.getRow(iRow);
        }
        return Personas;
    }

    public static String convertCellToString(Cell cell) {
        CellType cellType;
        String cellValue = "";
        cellType = cell.getCellType();
        switch (cellType) {
            case _NONE:
                cellValue = "";
                break;
            case BLANK:
                cellValue = "";
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                cellValue = String.valueOf(cell.getErrorCellValue());
                break;
            case NUMERIC:
                cellValue = String.valueOf((long)cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

}