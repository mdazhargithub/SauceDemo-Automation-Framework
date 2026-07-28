package com.azhar.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] getTestData(String excelFilePath, String sheetName) {
        Object[][] data = null;

        try {
            // 1. Open the physical file
            FileInputStream file = new FileInputStream(excelFilePath);

            // 2. Open the Workbook and the specific Sheet
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            // 3. Count how many rows and columns have data
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            // 4. Create the Java 2D array to hold the data
            data = new Object[rowCount][colCount];

            // 5. Loop through the Excel file and copy data into the Java array
            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    // Grab the text from the cell and save it
                    data[r][c] = sheet.getRow(r).getCell(c).getStringCellValue();
                }
            }

            // 6. Close the book to save computer memory
            workbook.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Could not find or read the Excel file: " + e.getMessage());
        }

        // 7. Hand the data over to TestNG
        return data;
    }
}
