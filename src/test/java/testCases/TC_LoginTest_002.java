package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.testBase;

public class TC_LoginTest_002 extends testBase{
	
	@Test(groups={"Sanity", "Master"})
	public void verifyLogin()
	{
		logger.info("*****Starting Login Test*****");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("******Clicked My Account******");
		hp.clickLogin();
		logger.info("******Clicked Login******");
		
		LoginPage lp = new LoginPage(driver);
		logger.info("******providing customer email******");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExist();
		
		//Assert.assertEquals(targetPage, true, "Login failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******Finished login test******");
		
	}

}
