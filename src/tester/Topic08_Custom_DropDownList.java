package tester;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic08_Custom_DropDownList {

	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	By btnNumber_Dropdown = By.id("number-button");
	By listNumber_Dropdown = By.xpath("//*[@id='number-menu']//div");
	By textNumber_Dropdown = By.xpath("//*[@id='number-button']/span[@class='ui-selectmenu-text']");

	By btnMenu_Angular = By.xpath("//ejs-dropdownlist[@id='games']");
	By listItems_Angular = By.xpath("//ul[@id='games_options']/li");
	By textSelected_Angular = By.xpath("//select[@id='games_hidden']/option[@selected]");

	By textInput_Editable = By.xpath("//input[@class='search']");
	By listItems_Editable = By.xpath("//div[@role='option']/span");
	By textVerify_Editable = By.xpath("//div[@class='divider text']");

	By btnMultiple = By.xpath("(//button[@class='ms-choice'])[1]");
	By listMonth_Multiple = By.xpath("(//div[@class='ms-drop bottom'])[1]//li//span");
	By textMonth_Multiple = By.xpath("(//button[@type='button'])[1]//span");

	String[] listMonths = { "January", "April", "September", "November", 
			"October",  "February", "March", "May", "June", "July", "August", "October", "December"};

	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown(btnNumber_Dropdown, listNumber_Dropdown, "10");
		Assert.assertEquals(driver.findElement(textNumber_Dropdown).getText(), "10");
		sleepInSeconds(1);

		selectItemInCustomDropdown(btnNumber_Dropdown, listNumber_Dropdown, "28");
		Assert.assertEquals(driver.findElement(textNumber_Dropdown).getText(), "28");
		sleepInSeconds(1);

		selectItemInCustomDropdown(btnNumber_Dropdown, listNumber_Dropdown, "11");
		Assert.assertEquals(driver.findElement(textNumber_Dropdown).getText(), "11");
		sleepInSeconds(1);

		selectItemInCustomDropdown(btnNumber_Dropdown, listNumber_Dropdown, "19");
		Assert.assertEquals(driver.findElement(textNumber_Dropdown).getText(), "19");
		sleepInSeconds(1);

	}

	public void TC_02_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInCustomDropdown(btnMenu_Angular, listItems_Angular, "Badminton");
		sleepInSeconds(1);
		Assert.assertEquals(getAngularDropdownSelectedText(), "Badminton");

		selectItemInCustomDropdown(btnMenu_Angular, listItems_Angular, "Golf");
		sleepInSeconds(1);
		Assert.assertEquals(getAngularDropdownSelectedText(), "Golf");

		selectItemInCustomDropdown(btnMenu_Angular, listItems_Angular, "Snooker");
		sleepInSeconds(1);
		Assert.assertEquals(getAngularDropdownSelectedText(), "Snooker");
	}

	public void TC_03_EditableDropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown(textInput_Editable, "Andorra");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(textVerify_Editable).getText(), "Andorra");

		selectItemInEditableDropdown(textInput_Editable, "Aland Islands");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(textVerify_Editable).getText(), "Aland Islands");

		selectItemInEditableDropdown(textInput_Editable, listItems_Editable, "Bahamas");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(textVerify_Editable).getText(), "Bahamas");

	}

	@Test
	public void TC_04_MultipleSelected() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		selectItemInMultipleDropdown(btnMultiple, listMonth_Multiple, listMonths);
		Assert.assertTrue(areItemSelected(listMonths));
	}

	public void selectItemInEditableDropdown(By parentXpath, String expectedText) {
		// Click on the dropdown
		driver.findElement(parentXpath).clear();
		driver.findElement(parentXpath).sendKeys(expectedText);
		sleepInSeconds(1);
		driver.findElement(parentXpath).sendKeys(Keys.ENTER);
	}

	public boolean areItemSelected(String[] listItemsSelectedText) {
		List<WebElement> listItemsSelected = driver.findElements(By.xpath("//li[@class='selected']"));
		int numberItemsSelected = listItemsSelected.size();

		String allItemsSelectedText = driver.findElement(textMonth_Multiple).getText();

		if (numberItemsSelected <= 3 && numberItemsSelected > 0) {
			for (String item : listItemsSelectedText) {
				if (allItemsSelectedText.contains(item)) {
					break;
				} else
					return false;
			}
			return true;
		} else if (numberItemsSelected > 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemsSelected > 3 && numberItemsSelected < 12) {
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemsSelected + " of 12 selected']"))
					.isDisplayed();
		} else
			return false;
	}

	public void selectItemInMultipleDropdown(By parentXpath, By allItemXpath, String[] expectedText) {
		// Click on the dropdown
		driver.findElement(parentXpath).click();
		sleepInSeconds(1);
		// Wait for all items are displayed
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemXpath));
//		List<WebElement> allItems = driver.findElements(allItemXpath);

		// presenceOfAllElements is return a list
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemXpath));
		for (String item : expectedText) {
			for (WebElement element : allItems) {
				if (element.getText().equals(item)) {
					element.click();
					break;
				}
			}
		}
	}

	public void selectItemInEditableDropdown(By parentXpath, By allItemXpath, String expectedText) {
		// Click on the dropdown
		driver.findElement(parentXpath).clear();
		driver.findElement(parentXpath).sendKeys(expectedText);
		sleepInSeconds(1);
		// Wait for all items are displayed
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemXpath));
//		List<WebElement> allItems = driver.findElements(allItemXpath);

		// presenceOfAllElements is return a list
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemXpath));

		for (WebElement element : allItems) {
			if (element.getText().equals(expectedText)) {
				element.click();
				break;
			}
		}
	}

	public void selectItemInCustomDropdown(By parentXpath, By allItemXpath, String expectedText) {
		// Click on the dropdown
		driver.findElement(parentXpath).click();

		// Wait for all items are displayed
//		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemXpath));
//		List<WebElement> allItems = driver.findElements(allItemXpath);

		// presenceOfAllElements is return a list
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemXpath));

		for (WebElement element : allItems) {
			if (element.getText().equals(expectedText)) {
				element.click();
				break;
			}
		}
	}

	public String getAngularDropdownSelectedText() {
//		return (String) jsExecutor.executeScript("return $(\"select[name='games']>option[selected]\").text");
		return (String) jsExecutor
				.executeScript("return document.querySelector(\"select[name='games']>option[selected]\").text");
	}

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	  driver.manage().window().maximize();
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

}
