package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	protected WebDriver driver;

	//Constructor for initialize WebDriver in each page object Class
	public BasePage(WebDriver driver) {
    this.driver= driver;
    PageFactory.initElements(driver, this);
	}
}