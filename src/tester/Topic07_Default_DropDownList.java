package tester;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Default_DropDownList {
	WebDriver driver;
	By btnRegisterPage = By.xpath("//a[text()='Register']");
	By cbMale = By.id("gender-male");
	By txtFirstName = By.id("FirstName");
	By txtLastName = By.id("LastName");
	By slDOB = By.xpath("//select[@name='DateOfBirthDay']");
	By slDOB_M = By.xpath("//select[@name='DateOfBirthMonth']");
	By slDOB_Y = By.xpath("//select[@name='DateOfBirthYear']");
	By txtEmail = By.id("Email");
	By txtCompany = By.id("Company");
	By cbNewsletter = By.id("Newsletter");
	By txtPassword = By.id("Password");
	By txtConfirmPassword = By.id("ConfirmPassword");
	By btnRegister = By.id("register-button");
	By btnContinue = By.xpath("//*[@name='register-continue']");
	By btnMyAccount = By.className("ico-account");
	Select select;
	String firstName, lastName, email, company, password, date, month, year;
	
	@Test
	public void TC_01_Register() {
		// TODO Auto-generated method stub
		driver.findElement(btnRegisterPage).click();
		checkToCheckbox(cbMale);
		driver.findElement(txtFirstName).sendKeys(firstName);
		driver.findElement(txtLastName).sendKeys(lastName);
		
		select = new Select(driver.findElement(slDOB));
		select.selectByVisibleText("19");
		sleepInSeconds(1);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(slDOB_M));
		select.selectByVisibleText("November");
		sleepInSeconds(1);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(slDOB_Y));
		select.selectByVisibleText("1994");
		sleepInSeconds(1);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(txtEmail).sendKeys(email);
		driver.findElement(txtCompany).sendKeys(company);
		checkToCheckbox(cbNewsletter);
		driver.findElement(txtPassword).sendKeys(password);
		driver.findElement(txtConfirmPassword).sendKeys(password);
		
		driver.findElement(btnRegister).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
//		driver.findElement(btnContinue).click();
		
		driver.findElement(btnMyAccount).click();
		Assert.assertTrue(driver.findElement(cbMale).isSelected());
		Assert.assertEquals(driver.findElement(txtFirstName).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(txtLastName).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(txtCompany).getAttribute("value"), company);
		Assert.assertEquals(driver.findElement(txtEmail).getAttribute("value"), email);
		Assert.assertTrue(driver.findElement(cbNewsletter).isSelected());
		select = new Select(driver.findElement(slDOB));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		select = new Select(driver.findElement(slDOB_M));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(slDOB_Y));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	}
	
	public void checkToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void sleepInSeconds(long millis) {
		try {
			Thread.sleep(millis *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRandomNumber() {
		Random number = new Random();
		return number.nextInt(9999);
	}
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.nopcommerce.com/");
//	  driver.manage().window().maximize();
		firstName = "Luan";
		lastName = "Nguyen";
		email = "lnt" + getRandomNumber() + "@hotmail.com";
		company = "Automa";
		password = "123456";
		date = "19";
		month = "November";
		year = "1994";
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
}
