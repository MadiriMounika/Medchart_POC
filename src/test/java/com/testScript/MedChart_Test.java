package com.testScript;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.driverScript.Driver;
import com.pages_script.Clients_Page;
import com.pages_script.Login_Pages;
import com.utils.ReadPropertiesFile;

public class MedChart_Test extends Driver {

	public static final String filename = null;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);
	Login_Pages login_pages;
	Clients_Page clients_Page;

	@BeforeClass
	public void init() throws InterruptedException {
		login_pages = new Login_Pages(driver);
		Driver.init(prop.getProperty("Browser"));
		login_pages.navigateTo_Login();
		System.out.println("Title is : " + Driver.driver.getTitle());
		Thread.sleep(10000);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".jpg"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test(priority = 1)
	public void createAlterRequest() throws Throwable {
		clients_Page = new Clients_Page(driver);
		login_pages = new Login_Pages(driver);
		login_pages.email_Input("qa.test.213@medchart.com");
		login_pages.password_Input("P@ssword99");
		login_pages.login_button();
		clients_Page.record();
		clients_Page.new_Request_button();
		clients_Page.state_Field();
		clients_Page.search_Button();
		clients_Page.record();
		clients_Page.records_checkbox();
		clients_Page.nextButton();
		clients_Page.present_checkbox();
		clients_Page.nextButton();
		clients_Page.addActionItems();
		String reqID = clients_Page.reqID();
		clients_Page.actionItems();
		clients_Page.reqIDDisplay(reqID);
		clients_Page.reqIDEdit(reqID);
	}
	
	@AfterTest
	public void quit() {
		Driver.driver.quit();
	}
}