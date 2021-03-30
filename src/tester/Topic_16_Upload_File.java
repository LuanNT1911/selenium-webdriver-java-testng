package tester;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String picture = projectPath + "\\uploadFile\\Picture.png";
	String chromeAutoIt = projectPath + "\\autoIt\\chromeUploadOneTime.exe";

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
//		action = new Actions(driver);
//		explicitWait = new WebDriverWait(driver, 10);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	public void sleepInSeconds(long millis) {
		try {
			Thread.sleep(millis * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	}
	
	
	public void TC_02_Upload_One_File_Per_Time_AutoIT() throws IOException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]")).click();
		
		Runtime.getRuntime().exec(new String[] {chromeAutoIt, picture});
	}
	
	@Test
	public void TC_03_Upload_One_File_Per_Time_Robot() throws IOException, AWTException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]")).click();
		
		//Specify the file location with the extension
		StringSelection selection = new StringSelection(picture);
		
		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		
		Robot robot = new Robot();
		sleepInSeconds(1);
		
		// Nhan phim Enter: Focus to textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		// Nhan Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		// Tha Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		sleepInSeconds(1);
		
		// Nhan Enter- Chon File
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
