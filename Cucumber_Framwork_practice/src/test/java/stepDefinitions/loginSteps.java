package stepDefinitions;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.baseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myaccountPage;

public class loginSteps {
	
	WebDriver d;
	Properties p;
	homePage hp;
	loginPage lp;
	myaccountPage map;
	
	@Given("user navigate to login page")
	public void user_navigate_to_login_page() {
		baseClass.getLogger().info("goto home page click login");
		hp=new homePage(baseClass.getDriver());
		hp.clickMyaccount();
		hp.clickLogin();
		
	   
	}

	@When("user enters Email {string} and password as {string}")
	public void user_enters_email_and_password_as(String string, String string2) 
	{
		baseClass.getLogger().info("enter username and password");
		lp=new loginPage(baseClass.getDriver());
		lp.setEmail(string);
		lp.setPassword(string2);
		
	}

	@When("Click on Login button")
	public void click_on_login_button() {
		baseClass.getLogger().info("click login");
		lp=new loginPage(baseClass.getDriver());
		lp.clickLogin();
	}

	@Then("user should redirect to MyAccount Page")
	public void user_should_redirect_to_my_account_page() {
	    
		baseClass.getLogger().info("user is on myaccount page");
		map=new myaccountPage(baseClass.getDriver());
		boolean conmsg=map.myaccountConfirmationMSG();
		Assert.assertEquals(true,conmsg);
	}


}
