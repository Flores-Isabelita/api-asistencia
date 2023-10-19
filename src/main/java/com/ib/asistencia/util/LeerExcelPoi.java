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
    public List<Persona> listar(String ruta) {
        int idEmpresa = 0;
        String turno = "";
        String nombre = "";
        String cedula = "";
        String celular = "";
        String proceso = "";
        String labor = "";
        LocalDate fechaActual = LocalDate.now();
        String fechaEnFormato = fechaActual.toString();
        List<Persona> personas = new ArrayList<>();
        //Abrir fichero de Excel
        try (FileInputStream inp = new FileInputStream(new File(ruta));
             Workbook wb = WorkbookFactory.create(inp)) {
            Sheet sheet = wb.getSheetAt(0);
            //Recorrer el fichero de Excel para obtener los datos
            int iRow = 3;
            Row row = sheet.getRow(iRow);
            while (row != null && row.getCell(0) != null) {
                    int colum = 0;
                while (colum < 50) {
                    Cell cell = row.getCell(colum);
                    String value = convertCellToString(cell);

                    switch (colum) {
                        case 0:
                            idEmpresa = Integer.parseInt(value);
                            colum++;
                            break;
                        case 1:
                            turno = value;
                            colum++;
                            break;
                        case 5:
                            labor = value;
                            colum++;
                            break;
                        case 9:
                            nombre = value;
                            colum++;
                            break;
                        case 11:
                            cedula = value;
                            colum++;
                            break;
                        case 26:
                            celular = value;
                            colum++;
                            break;
                        case 44:
                            proceso = value;
                            colum++;
                            break;
                        default:
                            colum++;
                            break;
                    }
                }

                Persona persona = new Persona();
                persona.setIdEmpresa((long) idEmpresa);
                persona.setTurno(turno);
                persona.setNombre(nombre);
                persona.setCedula(cedula);
                persona.setCelular(celular);
                persona.setProceso(proceso);
                persona.setLabor(labor);
                persona.setFechaActualizacion(fechaEnFormato);

                personas.add(persona);
                iRow++;
                row = sheet.getRow(iRow);
            }
        } catch (IOException e) {
            // Manejo de la excepción de E/S
            e.printStackTrace();
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
        }

        return personas;
    }

    public static String convertCellToString(Cell cell) {
        if (cell == null) {
            return "null";
        }
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