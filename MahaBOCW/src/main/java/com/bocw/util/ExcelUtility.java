package com.bocw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtility {

	@DataProvider(name="getData")
	public String[][] getData() throws Exception{	
		// Workbook -> sheet -> Rows -> Cells
		File excelFile = new File("./src/test/excelResources/MahaBOCW_LoginData.xlsx"); // . represents the file presents the current repository
		System.out.println(excelFile.exists());
		
		//for older version we need H and for newer verion we need to use 
		//xls(H), xlsx(x)
		
		//To read the data from the file
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int numberOfRows = sheet.getPhysicalNumberOfRows();
		int numberOfColumns = sheet.getRow(0).getLastCellNum();
		
		String[][] data= new String[numberOfRows -1][numberOfColumns];
		//To Read the data from the excel sheet
		for(int i=0; i<numberOfRows-1; i++) {
			for (int j=0; j< numberOfColumns; j++) {
				DataFormatter df = new DataFormatter(); //To convert the any type of data into String Format
				data[i][j]= df.formatCellValue(sheet.getRow(i+1).getCell(j));
				//System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
			}
		}
		
		
		//Read or Print the Data from the Excel sheet
		for(String[] dataArr : data) {
			System.out.println(Arrays.toString(dataArr));
		}
		return data;
	}

}
