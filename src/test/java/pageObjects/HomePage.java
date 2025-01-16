package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	Workbook workbook;
	Sheet sheet;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "txtStationFrom")
	WebElement fromStation;

	@FindBy(css = ".autocomplete>div")
	List<WebElement> stationList;

	public void selectStationFromDropDown(String stationName, int index) {
	
			fromStation.clear();
			fromStation.sendKeys(stationName);
			List<WebElement> newStations=new ArrayList<>(stationList);
			WebElement selectStation = newStations.get(index);
			String selectedStationName = selectStation.getDomAttribute("title");
			System.out.println("Selected Station: " + selectedStationName);
			selectStation.click();

	}

	

	    // Method to write the station list to Excel
	    public void writeStationListToExcel(String filePath) {
	        try {
	        	File file = new File(filePath);
	        	if (file.exists()) {
	        	    FileInputStream fis = new FileInputStream(file);
	        	    workbook = new XSSFWorkbook(fis);
	        	    fis.close();
	        	} else {
	        	    workbook = new XSSFWorkbook(); // Create a new workbook if file doesn't exist
	        	}
	        	 sheet = workbook.getSheet("Stations");
	             if (sheet == null) {
	                 sheet = workbook.createSheet("Stations");
	             }

	        	// Find the next available column
	            Row headerRow = sheet.getRow(0);
	            if (headerRow == null) {
	                headerRow = sheet.createRow(0);
	            }

	            int nextCol = headerRow.getLastCellNum();
	            if (nextCol == -1) {
	                nextCol = 0;
	            }

	            // Add header with timestamp
	            headerRow.createCell(nextCol).setCellValue("Run - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

	            // Write station names vertically under the new column
	            int rowNum = 1;
	            for (WebElement stationName : stationList) {
	                Row row = sheet.getRow(rowNum);
	                if (row == null) {
	                    row = sheet.createRow(rowNum);
	                }
	                String stationNames = stationName.getDomAttribute("title");
	                row.createCell(nextCol).setCellValue(stationNames);
	                rowNum++;
	            }

	            // Save the workbook
	            FileOutputStream outFile = new FileOutputStream(filePath);
	            workbook.write(outFile);
	            outFile.close();
	            workbook.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
