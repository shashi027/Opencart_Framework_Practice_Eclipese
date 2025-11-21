package stepDefinitions;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.baseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import pageObjects.homePage;

import pageObjects.myaccountPage;
import pageObjects.registerPage;

public class registrationSteps {

	WebDriver d;
	Properties p;
	homePage hp;
	registerPage rp;
	myaccountPage map;
	
	@Given("user navigates to registration account page")
	public void user_navigates_to_registration_account_page() 
	{
	    baseClass.getLogger().info("User navigated to account registration Page");
	    hp=new homePage(baseClass.getDriver());
	    hp.clickMyaccount();
	    hp.clickRegister();
	    
	}

	@When("users enters the below credentials")
	public void user_users_enters_the_below_credentials(DataTable dataTable) 
	{
		Map<String, String> dataMap = dataTable.asMap(String.class,String.class);
		rp=new registerPage(baseClass.getDriver());
		rp.setFirstName(dataMap.get("firstName"));
		rp.setLastName(dataMap.get("lastName"));
		rp.setEmail(baseClass.randomAlphaNumeric()+"@gmail.com");
		rp.setMobileNumber(dataMap.get("telephone"));
		rp.setPassword(dataMap.get("password"));
		rp.setConfirmPassword(dataMap.get("password"));
		
	    baseClass.getLogger().info("User Enters the details in account creation page");
	}

	@When("user select Privacy Policy")
	public void user_select_privacy_policy() {
	    rp=new registerPage(baseClass.getDriver());
	    rp.acceptPrivacyPolicy();
	    baseClass.getLogger().info("User Click on Privacy Policy");
	}

	@When("user clicks on Continue button")
	public void user_click_on_continue_butoon() {
		rp=new registerPage(baseClass.getDriver());
	    rp.clickContinue();
	}

	@Then("user account creation should be successful")
	public void user_acoount_creation_should_be_successful() {
		rp=new registerPage(baseClass.getDriver());
		String conmsg=rp.getConfirmationMessage();
		Assert.assertEquals(conmsg,"Your Account Has Been Created!");
	}
	
}
