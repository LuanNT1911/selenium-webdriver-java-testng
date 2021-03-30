package tester;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Window_Tab {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSeconds(long millis) {
		try {
			Thread.sleep(millis * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void TC_01_Only_2_Window_Or_Tab() {
		driver.get("https://kyna.vn/");

		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID: " + parentID);

		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();

		switchToWindowByID(parentID);

		Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));
	}

	@Test
	public void TC_02_Greater_Than_2_Windows_Or_Tabs() {
		driver.get("https://kyna.vn/");
		
		String kynaID = driver.getWindowHandle();

		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();

		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna"));
		
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='zalo']")).click();
		switchToWindowByTitle("Zalo Official Account");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna"));
		
		closeAllWindowsExceptParent(kynaID);
		
		Assert.assertEquals(driver.getWindowHandles().size(), 1);
	}
	
	public void closeAllWindowsExceptParent(String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for(String id: allIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSeconds(1);
			}
		}
	}
	
	public void switchToWindowByID(String windowID) {
		Set<String> allIds = driver.getWindowHandles();

		for (String id : allIds) {
			System.out.println("Current ID: " + id);
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(String title) {
		Set<String> allIds = driver.getWindowHandles();
		
		for (String id : allIds) {
			driver.switchTo().window(id);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.equals(title)) {				
				break;
			}
		}
	}
}
