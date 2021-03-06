package appHooks;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.ExcelDataCollection;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	public ConfigReader configReader;
	Properties prop;
	public Scenario scenario;
	public static String ScenarioName;
	
	
	
	@Before(order = 0)
	public void getProperty() throws IOException {
		
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		
	}
	
	@Before(order=1)
	
	public void getExcelData() throws IOException
	{
		String path ="C://Users//HP//Desktop//TestDataSheet.xlsx";
		
		ExcelDataCollection.globalExcelData=ExcelDataCollection.sheetData(path);
		ExcelDataCollection.scenarioSheetData=ExcelDataCollection.getSheettoData("Sheet1");
		//extentReport.initiazationReport();
		//GlobalMethods.CreateTestsRunDirectory();
		
	}
	
	@Before(order = 2)
	public void getscenario(Scenario scenario) {
	    this.scenario = scenario;
	   ScenarioName = scenario.getName();
	}

	@Before(order = 3)
	public void launchBrowser() {
		
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
		
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}
	
	

}
