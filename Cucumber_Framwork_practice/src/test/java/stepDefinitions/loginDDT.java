package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.baseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myaccountPage;
import utilities.DataReader;

public class loginDDT {
	
	WebDriver d;
	Properties p;
	homePage hp;
	loginPage lp;
	myaccountPage map;
	List<HashMap<String,String>>datamap;
	
	@Given("User Navigates to Login Page.")
	public void user_navigates_to_login_page() {
	   
		hp=new homePage(baseClass.getDriver());
		hp.clickMyaccount();
		hp.clickLogin();
		
		baseClass.getLogger().info("User navigates to login Page");
		
	}

	@Then("User should redirect to Myaccount page by entering email and password with excel row {string}")
	public void user_should_redirect_to_myaccount_page_by_entering_email_and_password_with_excel_row(String rows) throws IOException {
		
		try {
		datamap = DataReader.data("./testData/Opencart_LoginData.xlsx", "Sheet1");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		int index=Integer.parseInt(rows)-1;
		String email=datamap.get(index).get("username");
		String pwd=datamap.get(index).get("password");
		String exp_res=datamap.get(index).get("res");
		
		lp=new loginPage(baseClass.getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		map=new myaccountPage(baseClass.getDriver());
		
		
		try {

			boolean targetpage = map.myaccountConfirmationMSG();

			if (exp_res.equalsIgnoreCase("valid")) {
				if (targetpage == true) {
					map.clickLogoutbtn();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			}

			if (exp_res.equalsIgnoreCase("invalid")) {
				if (targetpage == false) {
					Assert.assertTrue(true);
				} 
				else {
					
					map.clickLogoutbtn();
					Assert.assertTrue(false);
				}

			}
		
		
		}
		
		
	    catch(Exception e)
	    {
	    	Assert.assertTrue(false);
	    }
	}
}
