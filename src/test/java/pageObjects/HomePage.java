package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnk_MyAcount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnk_Register;
	
	@FindBy (xpath = "//a[normalize-space()='Login']")
	WebElement lnk_login;
	
	public void clickMyAccount()
	{
		lnk_MyAcount.click();
	}
	
	public void clickRegister()
	{
		lnk_Register.click();
	}
	
	public void clickLogin()
	{
		lnk_login.click();
	}

}
