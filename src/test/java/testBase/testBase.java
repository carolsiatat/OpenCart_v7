package testBase;

import java.io.File;



import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
//import java.net.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;   //log4j
//import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Logger;     //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class testBase {
	
public static  WebDriver driver;
public Logger logger;
public Properties p;
	

	@BeforeClass(groups= {"Sanity", "Regression","Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException   //String os, String browser
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());  //log4j2 code
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setPlatform(Platform.WIN10);
			//capabilities.setBrowserName("chrome");
			
			//os from config.prperties - remote environment
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("Invalid browser name"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);    //wd/hub
			
			/*URL hp = null;
			try{
			        hp = new URL("http://192.168.10.1:4444//wd/hub");
			        System.out.println("it all worked?");
			        System.out.println(hp.getProtocol());
			    }catch (MalformedURLException e){
			        System.err.println("New URL failed");
			        System.err.println("exception thrown: " + e.getMessage());
			    }*/
		
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		//takes browsers from the master.xml file (Run only from xml to work)
		//LOCAL ENVIRONMENT
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default: System.out.println("Invalid browser name"); return;
		}
		}
	
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); //reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	public String randomString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	@SuppressWarnings("deprecation")
	public String randomNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(9);
		return generatednumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphanumeric()
	{
		String letters = RandomStringUtils.randomAlphabetic(5);
		String numbers = RandomStringUtils.randomNumeric(9);
		return (letters+numbers);
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile= new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}

}
