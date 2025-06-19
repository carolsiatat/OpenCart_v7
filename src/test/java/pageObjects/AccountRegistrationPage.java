package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtCpassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement btnPolicyAgree;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnClickContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtCpassword.sendKeys(pwd);
	}
	
	public void clickPrivacyPolicy()
	{
		btnPolicyAgree.click();;
	}
	
	public void clickContinue()
	{
		btnClickContinue.click();;
	}
	
	public String getConfirmationMsg() {
		try {
			return(msgConfirmation.getText());
		} catch (Exception e) {
			return(e.getMessage());
		}
	}
	
	
	
	

}
