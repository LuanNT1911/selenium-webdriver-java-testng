package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Loop {

    WebDriver driver;
    By firstNameTextbox = By.xpath("//*[@id='firstname']");
    By lastnameTextbox = By.xpath("//*[@id='lastname']");
    By emailTextbox = By.xpath("//*[@id='email_address']");
    By passwordTextbox = By.xpath("//*[@id='password']");
    By confirmationTextbox = By.xpath("//*[@id='confirmation']");
    By registerButton = By.xpath("//button[@title='Register']");
    By errorMsg = By.xpath("//li[@class='error-msg']//span");

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlValue){
        if(browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//browserDriver//geckodriver.exe");
            driver = new FirefoxDriver();
        }else if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//browserDriver//chromedriver.exe");
            driver = new ChromeDriver();
        }else{
            throw new RuntimeException("Undefined the browser");
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(urlValue);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @Test(dataProvider = "create_user", invocationCount = 5)
    public void TC_01_Create_User(String username, String password){
        driver.findElement(firstNameTextbox).sendKeys("Test");
        driver.findElement(lastnameTextbox).sendKeys("Auto");
        driver.findElement(emailTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(confirmationTextbox).sendKeys(password);
        driver.findElement(registerButton).click();

        Assert.assertEquals(driver.findElement(errorMsg).getText(),"There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.");
        driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
    }

//    @DataProvider(name = "create_user")
//    public Object[][] userAndPasswordData(){
//        return new Object[][]{
//                {"auto" + random() + "@gmail.com", "111111"},
//                {"auto" + random() + "@gmail.com", "111111"},
//                {"auto" + random() + "@gmail.com", "111111"}};
//    }

    @DataProvider(name = "create_user")
    public Object[][] userAndPasswordData(){
        return new Object[][]{
                {"45678@gmail.com", "111111"}};
    }

    public int random(){
        Random random = new Random();
        return  random.nextInt(9999);
    }
}
