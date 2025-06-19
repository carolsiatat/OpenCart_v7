package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.testBase;

public class TC_AccountRegistration_001 extends testBase{
	
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration()
	{
		
		logger.info("******Starting test case******");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("******Clicked My Account******");
		hp.clickRegister();
		logger.info("******Clicked Register******");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("******providing customer details******");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() +"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password = randomAlphanumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.clickPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("******validating expected message******");
		String cnfmsg = regpage.getConfirmationMsg();
		
		if(cnfmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(cnfmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e)
		{
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
		logger.info("***Account registration successful***");
	}
		

}
