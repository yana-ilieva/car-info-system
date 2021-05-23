package com.example.carinfosystem.util;

import com.example.carinfosystem.model.Car;
import com.example.carinfosystem.model.Extra;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Car> carList;

    public ExcelExporter(List<Car> carList){
        this.carList = carList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workbook.createSheet("Deleted cars");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Brand", style);
        createCell(row, 1, "Model", style);
        createCell(row, 2, "Registration Number", style);
        createCell(row, 3, "Production Year", style);
        createCell(row, 4, "Fuel Type", style);
        createCell(row, 5, "Car Type", style);
        createCell(row, 6, "Color", style);
        createCell(row, 7, "Extras", style);
        createCell(row, 8, "DeletedAt", style);
        createCell(row, 9, "City", style);
        createCell(row, 10, "Branch", style);
    }

    private void createCell(Row row, int colCount, Object value, CellStyle style){
        sheet.autoSizeColumn(colCount);
        Cell cell = row.createCell(colCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof String){
            cell.setCellValue((String) value);
        } else if(value instanceof LocalDateTime){
            cell.setCellValue((LocalDateTime) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines(){
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy hh:mm:ss"));
        dateStyle.setFont(font);

        for(Car c : carList){
            Row row = sheet.createRow(rowCount++);
            int colCount = 0;

            createCell(row, colCount++, c.getModel().getBrand().getName(), style);
            createCell(row, colCount++, c.getModel().getName(), style);
            createCell(row, colCount++, c.getRegistrationNumber(), style);
            createCell(row, colCount++, c.getProductionYear(), style);
            createCell(row, colCount++, c.getFuelType().name(), style);
            createCell(row, colCount++, c.getCarType().name(), style);
            createCell(row, colCount++, c.getColor().name(), style);
            StringBuilder sb = new StringBuilder();
            for (Extra extra : c.getExtras()) {
                sb.append(extra.getName()).append(", ");
            }
            createCell(row, colCount++, sb.toString(), style);
            createCell(row, colCount++, c.getDeletedAt(), dateStyle);
            createCell(row, colCount++, c.getBranch().getCity().getName(), style);
            createCell(row, colCount++, c.getBranch().getName(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
