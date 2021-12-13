package com.pages_script;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverScript.Driver;
import com.utils.ReadPropertiesFile;

public class Clients_Page extends Driver {
	public static final String filename = null;
	public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public Properties prop = readPropertiesFile.readPropertiesFile(filename);
	
	@FindBy(xpath = "//tbody[@class='p-datatable-tbody']/tr[1]")
	WebElement table_record;
	
	@FindBy(xpath = "//h3[contains(text(),'Client')]/../../div/div/div[3]/div/button")
	WebElement new_Request;
	
	@FindBy(name = "organizationName")
	WebElement org_Name;
	
	@FindBy(xpath = "//div[contains(text(),'(Select)')]")
	WebElement state;
	
	@FindBy(xpath = "//input[contains(@id,'react-select')]")
	WebElement state_sel;
	
	@FindBy(xpath= "//div[contains(text(),'Fill')]/../../div/button")
	WebElement search_Button;
	
	@FindBy(xpath ="(//input[@type='checkbox'])[1]")
	WebElement records_checkbox;
	
	@FindBy(xpath ="//button[contains(text(),'Next')]")
	WebElement next_button;
	
	@FindBy(xpath ="(//input[@type='checkbox'])[1]")
	WebElement present_checkbox;
	
	@FindBy(xpath="//button[contains(text(),'Add to Action Items')]")
	WebElement AddActionItems;
	
	@FindBy(xpath="(//div[@class='alert alert-success fade show'])[2]")
	WebElement reqID;
	
	@FindBy(xpath="//a[contains(text(),'Action Items')]")
	WebElement actionItems;
	
	public Clients_Page(WebDriver driver) {
		Driver.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void record() throws InterruptedException {
		table_record.click();
		Thread.sleep(10000);
	}
	
	public void new_Request_button() throws InterruptedException {
		new_Request.click();
		Thread.sleep(10000);
	}
	
	public void org_Name_Field(String org) {
		org_Name.sendKeys(org);
	}
	
	public void state_Field() throws InterruptedException {
		state.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		state_sel.sendKeys("Alabama - AL");
		state_sel.sendKeys(Keys.TAB);
	}
	
	public void search_Button() throws InterruptedException {
		search_Button.click();
		Thread.sleep(10000);
	}
	
	public void records_checkbox() {
		records_checkbox.click();
	}
	
	public void present_checkbox() {
		present_checkbox.click();
	}
	
	public void nextButton() throws InterruptedException {
		next_button.click();
		Thread.sleep(10000);
	}
	
	public void addActionItems() {
		AddActionItems.click();
	}
	
	public String reqID() {
		String text = reqID.getText();
		String words[] = text.split(" ");
		return words[2];
	}
	
	public void actionItems() {
		actionItems.click();
	}
	
	public boolean reqIDDisplay(String reqID) {
		driver.findElement(By.xpath("//div[contains(text(),'"+reqID+"')]")).isDisplayed();
		return true;
	}
	
	public void reqIDEdit(String reqID) {
		driver.findElement(By.xpath("//div[contains(text(),'"+reqID+"')]/../../../div[2]/div/div/button")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Edit Request')]")).click();
		driver.findElement(By.xpath("//h5[contains(text(),'Records')]/../textarea")).sendKeys("Test");
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
	}
}
