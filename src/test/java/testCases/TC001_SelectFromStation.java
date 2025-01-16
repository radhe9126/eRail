package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseTest;

public class TC001_SelectFromStation extends BaseTest {

	
	@Test
	public void selectStationByIndex() {
		HomePage hp=new HomePage(driver);
		hp.selectStationFromDropDown("DE", 3);
	}
	
	@Test
	public void writeIntoExcel() throws IOException {
		HomePage hp=new HomePage(driver);
		String filePath=System.getProperty("user.dir") +"/testData/StationName.xlsx";
		hp.writeStationListToExcel(filePath);
	}
	
	
}
