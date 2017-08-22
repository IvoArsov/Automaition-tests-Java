package testCases;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class TestLogin {

    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("http://localhost:3000/#"); // To run this tests, must download project from GitHub and run it local
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    //TODO: Before testLogin, must write testRegister, to get fresh data for login

    @Test
    public void testLogin_validCredentials_loginSuccess(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();
        loginPage.typeUsername("ivo");
        loginPage.typePassword("123");
        loginPage.clickLogin();

        //this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement result = this.driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div[1]/div/div/div[2]/div/span"));
        Assert.assertEquals(result.getText(), "Welcome, ivo!");
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
