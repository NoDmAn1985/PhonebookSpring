package ru.academits.savetoexcel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import ru.academits.entity.Contact;

public class SaveToExcel {

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        setEqualsParameters(style);
        return style;
    }

    private static HSSFCellStyle createStyleForOther(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        setEqualsParameters(style);
        return style;
    }

    private static void setEqualsParameters(HSSFCellStyle style) {
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
    }

    public static void save(List<Contact> list) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Phonebook");

        int rownum = 0;
        Cell cell;
        Row row;

        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№");
        sheet.setColumnWidth(0, 1000);
        cell.setCellStyle(style);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Id");
        sheet.setColumnWidth(1, 1000);
        cell.setCellStyle(style);

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Фамилия");
        sheet.setColumnWidth(2, 4000);
        cell.setCellStyle(style);

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Имя");
        sheet.setColumnWidth(3, 4000);
        cell.setCellStyle(style);

        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue("Телефон");
        sheet.setColumnWidth(4, 4000);
        cell.setCellStyle(style);

        style = createStyleForOther(workbook);

        for (Contact contact : list) {
            ++rownum;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(rownum);
            cell.setCellStyle(style);

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(contact.getId());
            cell.setCellStyle(style);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(contact.getLastName());
            cell.setCellStyle(style);

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(contact.getFirstName());
            cell.setCellStyle(style);

            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(contact.getPhone());
            cell.setCellStyle(style);

        }
        File file = new File("temp.xls");
        try {
            workbook.write(new FileOutputStream(file));
            try {
                String command = "excel \"" + file.getAbsolutePath() + "\"";
                Runtime.getRuntime().exec("cmd /c start " + command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
