package com.pages_script;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverScript.Driver;
import com.utils.ReadPropertiesFile;

public class Login_Pages extends Driver{
	
	public static final String filename = null;
	public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public Properties prop = readPropertiesFile.readPropertiesFile(filename);
	
	@FindBy(id = "Input_Email")
	WebElement email;
	
	@FindBy(id = "Input_Password")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement login_button;

	public Login_Pages(WebDriver driver) {
		Driver.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void navigateTo_Login() {
		driver.get(prop.getProperty("URL"));
	}
	
	public void email_Input(String emailValue) {
		email.sendKeys(emailValue);
	}
	
	public void password_Input(String passwordValue) {
		password.sendKeys(passwordValue);
	}
	
	public void login_button() throws InterruptedException {
		login_button.click();
		Thread.sleep(15000);
	}
}
