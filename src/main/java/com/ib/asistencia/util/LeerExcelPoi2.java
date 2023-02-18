package com.ib.asistencia.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeerExcelPoi2 {

    public static List lectura(String ruta) {
        List cellRow = new ArrayList<>();
        List rows = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(new File("C:\\Users\\daniel.guzman\\Documents\\Hoja.xlsx"))) {
            // leer archivo excel
            XSSFWorkbook worbook = new XSSFWorkbook(file);
            // obtener la hoja que se va leer
            XSSFSheet sheet = worbook.getSheetAt(0);
            // obtener todas las filas de la hoja excel
            Iterator<Row> rowIterator = sheet.iterator();

            Row row;
            // se recorre cada fila hasta el final
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // se obtiene las celdas por fila
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell;
                // se recorre cada celda
                while (cellIterator.hasNext()) {
                    // se obtiene la celda en específico y se la imprime
                    cell = cellIterator.next();
                    cellRow.add(cell);
                }

                rows.add(cellRow);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        
        return rows;
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
                cellValue = String.valueOf(cell.getNumericCellValue());
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

    public Object EnviarSql(String string) {
        return null;
    }
}