package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	public static WebDriver driver;
	
	@BeforeClass
	public void setup() {
		driver=new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	public String captureScreen(String tname) throws IOException {
		
		if (driver == null) {
	        throw new IllegalStateException("WebDriver is not initialized. Ensure the driver is set up before capturing screenshots.");
	    }

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = ((TakesScreenshot)driver);
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir") + "/Screenshots/" + tname + timeStamp + ".png";;
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}


}