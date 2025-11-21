package factory;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class baseClass {

	static WebDriver d;
	static Properties p;
	static Logger logger;
	
	//1
	public static Properties getProperties() throws IOException
	{
		FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		return p;
	}
	
	
	
	//2
	@SuppressWarnings("deprecation")
	public static WebDriver initializeBrowser() throws IOException
	{
		
		String os=getProperties().getProperty("os");
		String browser=getProperties().getProperty("browser");
		String env=getProperties().getProperty("execution_env");
		
		if(env.equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			switch(os.toLowerCase())
			{
			case "windows":
				cap.setPlatform(Platform.WIN11);
				break;
			case "mac":
				cap.setPlatform(Platform.MAC);
				break;
			case "linux":
				cap.setPlatform(Platform.LINUX);
				break;
			default :
				System.out.println("Invalid os type provided");
				return null;
			
			}
			
			switch(browser.toLowerCase())
			{
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge":cap.setBrowserName("MicosoftEdge");break;
			default :System.out.println("invalid browser");
			return null;
			}
			
			d=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
		} //remote
		
		else if(env.equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome":d=new ChromeDriver();break;
			case "edge":d=new EdgeDriver();break;
			case "firefox":d=new FirefoxDriver();break;
			default :System.out.println("Invalid browser name");
			return null;
			}
		
		}
		d.manage().deleteAllCookies();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		return d;
	
	}//method
	
	public static WebDriver getDriver()
	{
		return d;
	}
	
	public static Logger getLogger() 
	{		 
		logger=LogManager.getLogger(); //Log4j
		return logger;
	}
	
	
	public static String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	
	public static String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
		
	public static String randomAlphaNumeric()
	{
	String str=RandomStringUtils.randomAlphabetic(5);
	 String num=RandomStringUtils.randomNumeric(10);
	return str+num;
	}
	
}
