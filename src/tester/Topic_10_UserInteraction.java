package tester;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteraction {
	WebDriver driver;
	Actions action;
	JavascriptExecutor javascriptExecutor;
	String projectPath = System.getProperty("user.dir");
	String javascriptPath = projectPath + "\\dragAndDropJS\\drag_and_drop_helper.js";
	String jqueryPath = projectPath + "\\dragAndDropJS\\jquery_load_helper.js";

	By tag_a_hover = By.xpath("//a[normalize-space()='ThemeRoller']");
	By text_hover = By.xpath("//div[@class='ui-tooltip-content']");
	By list_Number = By.xpath("//*[@id='selectable']/li");
	By list_NumberSelected = By.xpath("//*[@id='selectable']/li[contains(@class,'ui-selected')]");
	By btn_DoubleClick = By.xpath("//button[text()='Double click me']");
	By textDoubleClick = By.id("demo");
	By btn_RightClick = By.xpath("//span[text()='right click me']");
	By text_Menu_Quit = By.xpath("//span[text()='Quit']");
	By text_Menu_Quit_Hover = By.xpath("//li[contains(@class,' context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]");
	
	public void TC_01_Hover_Mouse() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(tag_a_hover)).perform();
		sleepInSeconds(2);
		Assert.assertEquals(driver.findElement(text_hover).getText(),
				"ThemeRoller: jQuery UI's theme builder application");
	}

	public void TC_02_Click_And_Hover() {
		// TODO Auto-generated method stub
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> listNumber = driver.findElements(list_Number);
		Assert.assertEquals(listNumber.size(), 12);
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(3)).release().perform();

		List<WebElement> listNumberSelected = driver.findElements(list_NumberSelected);
		Assert.assertEquals(listNumberSelected.size(), 4);
		
	}
	
	public void TC_03_Click_And_Hover_Random() {
		// TODO Auto-generated method stub
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> listNumber = driver.findElements(list_Number);
		Assert.assertEquals(listNumber.size(), 12);
		action.keyDown(Keys.CONTROL).perform();
		action.click(listNumber.get(1)).click(listNumber.get(2)).click(listNumber.get(5)).click(listNumber.get(8)).perform();
		action.keyDown(Keys.CONTROL).perform();
		
		List<WebElement> listNumberSelected = driver.findElements(list_NumberSelected);
		Assert.assertEquals(listNumberSelected.size(), 4);
		
	}


	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(btn_DoubleClick)).perform();
		Assert.assertEquals(driver.findElement(textDoubleClick).getText(), "Hello Automation Guys!");
	}
	
	
	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(btn_RightClick)).perform();
		sleepInSeconds(1);
		Assert.assertTrue(driver.findElement(text_Menu_Quit).isDisplayed());
		
		action.moveToElement(driver.findElement(text_Menu_Quit)).perform();
		Assert.assertTrue(driver.findElement(text_Menu_Quit_Hover).isDisplayed());
		
		action.click(driver.findElement(text_Menu_Quit)).perform();
		sleepInSeconds(1);
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
		
		Assert.assertFalse(driver.findElement(text_Menu_Quit).isDisplayed());
	}
	

	public void TC_06_Drag_Drop_HTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		
		WebElement sourceCircle = driver.findElement(By.id("draggable"));
		WebElement targetCircle = driver.findElement(By.id("droptarget"));
		action.moveToElement(sourceCircle);
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSeconds(1);
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		Assert.assertEquals(getHexColor(targetCircle.getCssValue("background-color")), "#03a9f4");
	}
	
	
	public void TC_07_Drag_And_Drop_HTML5_ByCSS() throws InterruptedException, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		// B to A
		javascriptExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}
	
	@Test
	public void TC_07_DragDrop_HTML5_Xpath() throws InterruptedException, IOException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
	
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
	}
	
	
	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getHexColor(String RGBColor) {
		return Color.fromString(RGBColor).asHex();
	}
	
	
	public void sleepInSeconds(long millis) {
		try {
			Thread.sleep(millis * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}


	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
}
