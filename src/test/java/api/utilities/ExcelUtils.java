package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sh;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public ExcelUtils(String path) {
        this.path = path;
    }

    public int getRowCount(String xlFile, String sheetName) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(sheetName);
        int rowCount = sh.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String xlFile, String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(sheetName);
        row = sh.getRow(rownum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String xlFile, String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(sheetName);
        row = sh.getRow(rownum);
        cell = row.getCell(colnum);

        String data;
        try {
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
        }
        catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    public void setCellData(String xlFile, String sheetName, int rownum, int colnum, String data) throws IOException {
        File file = new File(xlFile);

        // If file doesn't exist, create a new workbook
        if (!file.exists()) {
            XSSFWorkbook newWb = new XSSFWorkbook();
            newWb.createSheet(sheetName);
            FileOutputStream fos = new FileOutputStream(file);
            newWb.write(fos);
            fos.close();
            newWb.close();
        }

        FileInputStream fi = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet sh = wb.getSheet(sheetName);

        // If sheet doesn't exist, create it
        if (sh == null) {
            sh = wb.createSheet(sheetName);
        }

        // If row doesn't exist, create it
        XSSFRow row = sh.getRow(rownum);
        if (row == null) {
            row = sh.createRow(rownum);
        }

        // If cell doesn't exist, create it
        XSSFCell cell = row.getCell(colnum);
        if (cell == null) {
            cell = row.createCell(colnum);
        }

        cell.setCellValue(data);

        fi.close(); // Close input before writing

        FileOutputStream fo = new FileOutputStream(file);
        wb.write(fo);

        wb.close();
        fo.close();
    }
}