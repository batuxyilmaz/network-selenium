package utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

//Excel dosyasını okuyor.
public class Excel_Utility {
    public static List<List<String>> getData(String path, String sheetName, int columnCount){

        //Verileri içine alıyor.
        List<List<String>> list = new ArrayList<>();

        Workbook workbook = null;
        try {
            FileInputStream inputStream = new FileInputStream(path);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception ex) {
        }

        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();

        //2d olarak verileri okuyor.
        for (int i = 0; i < rowCount; i++) {

            List<String> rowList = new ArrayList<>();
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                rowList.add(row.getCell(j).toString());
            }

            list.add(rowList);
        }

        return list;
    }


}








