package hooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import factory.baseClass;
import io.cucumber.java.*;

public class Hooks {
	
	WebDriver d;
	Properties p;
	
	@Before
	public void setup() throws IOException
	{
		d=baseClass.initializeBrowser();
		p=baseClass.getProperties();
		d.get(p.getProperty("appURL"));
		d.manage().window().maximize();
	}

	
	@After
	public void teardown()
	{
		d.quit();
	}
	
	
	@AfterStep
	public void takeScreenshot(Scenario Scenario)
	{
		
		if(Scenario.isFailed())
		{
			TakesScreenshot ts=(TakesScreenshot) d;
			byte[] sc = ts.getScreenshotAs(OutputType.BYTES);
			Scenario.attach(sc, "image/png", Scenario.getName());
		}
		
	}
	
}
