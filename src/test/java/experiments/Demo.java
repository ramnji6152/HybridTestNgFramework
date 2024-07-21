package experiments;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

public class Demo {
    @Test
    public static String utilities(){
        Date date = new Date();
        String timestampDate = date.toString().replace(" ","_").replace(":","_");
        String email = "chitti8142"+timestampDate+"@gmail.com";
        return email;
    }

    public static Object[][] getTextDataFromExcel(String sheetName){
        XSSFWorkbook workbook = null;
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\TestData.xlsx");
        try {
            FileInputStream fisExcel = new FileInputStream(file);
            workbook = new XSSFWorkbook(fisExcel);
        } catch (Throwable e){
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for(int i=0; i<rows; i++){
            XSSFRow row = sheet.getRow(i+1);
            for(int j=0; j<cols; j++){
               XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();

                switch (cellType) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] =  Integer.toString((int) cell.getNumericCellValue());
                        break;
                }
            }
        }
        return data;
    }

}
