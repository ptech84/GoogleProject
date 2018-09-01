package com.src.google.testutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.src.google.base.TestBase;

public class TestUtil extends TestBase{
	
	
public static String TESTDATA_SHEET_PATH = "/Users/praveennaidu/eclipse-workspace/GoogleProject/src/main/java/com/src/google/testdata/FreeCrmTestData.xlsx";

static Workbook book;
static Sheet sheet;


public static Object[][] getTestData(String sheetName) {
	try {
	  FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
		book = WorkbookFactory.create(fis);
		sheet = book.getSheet(sheetName);
	} catch (InvalidFormatException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	  
	  for(int i=0;i<sheet.getLastRowNum();i++) {
		  for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
			  data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			 	  
		  }
	  }
	  return data;
	
}

}