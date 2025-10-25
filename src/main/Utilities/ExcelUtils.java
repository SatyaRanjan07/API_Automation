import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;

    public static int getRowCount(String xlFile, String sheetName) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(sheetName);
        int rowCount = sh.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public static int getCellCount(String xlFile, String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(sheetName);
        row = sh.getRow(rownum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public static String getCellData(String xlFile, String sheetName, int rownum, int colnum) throws IOException {
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

    public static void setCellData(String xlFile, String sheetName, int rownum, int colnum, String data) throws IOException {
        fi = new FileInputStream(xlFile);
        wb = new XSSFWorkbook(fi);
        sh = wb.getSheet(sheetName);
        row = sh.getRow(rownum);
        cell = row.getCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(xlFile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
}