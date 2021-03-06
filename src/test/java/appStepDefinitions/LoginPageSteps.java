package appStepDefinitions;

import java.util.Properties;

import org.junit.Assert;

import appHooks.ApplicationHooks;
import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.ExcelDataCollection;

public class LoginPageSteps {

	private static String title;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	
	public ConfigReader configReader;
	Properties prop;
	

	@Given("user is on login page")
	public void user_is_on_login_page() {
		
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		
		String URL = prop.getProperty("AppUrl");
		DriverFactory.getDriver()
				.get(URL);
		
		//extentReport.stepStatus("Launching Application :", "Pass", true);
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		title = loginPage.getLoginPageTitle();
		System.out.println("Page title is: " + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {
		Assert.assertTrue(title.contains(expectedTitleName));
	}

	@Then("forgot your password link should be displayed")
	public void forgot_your_password_link_should_be_displayed() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {
		
		String UName=ExcelDataCollection.getSpecificKeyValue(ExcelDataCollection.scenarioSheetData,ApplicationHooks.ScenarioName, username);
		loginPage.enterUserName(UName);
	}

	@When("user enters password {string}")
	public void user_enters_password(String Password) {
		String UPassword=ExcelDataCollection.getSpecificKeyValue(ExcelDataCollection.scenarioSheetData,ApplicationHooks.ScenarioName, Password);
		loginPage.enterPassword(UPassword);
	}

	@When("user clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.clickOnLogin();
	}


}
