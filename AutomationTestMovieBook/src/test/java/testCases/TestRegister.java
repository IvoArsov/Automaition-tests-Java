package testCases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegisterPage;

import java.sql.Time;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestRegister {

    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("http://localhost:3000/#"); // To run this tests, must download project from GitHub and run it local
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testRegistration_validCredentials_registrationSuccess_9(){

        RegisterPage registerPage = new RegisterPage(driver);

        String uniqueUsername = UUID.randomUUID().toString();

        registerPage.navigateToRegisterPage();
        registerPage.typeUsername(uniqueUsername);
        registerPage.typePassword("1234567");
        registerPage.typeConfirmPassword("1234567");
        registerPage.typeEmail("test@gmail.com");
        registerPage.clickRegister();

        WebElement result = this.driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div[1]/div/div/div[2]/div/span"));
        Assert.assertEquals(result.getText(), "Welcome, " + uniqueUsername + "!");
    }

    @Test
    public void testRegistration_validCredentialsWithoutEmail_registrationSuccess_10(){
        RegisterPage registerPage = new RegisterPage(driver);

        String uniqueUsername = UUID.randomUUID().toString();

        registerPage.navigateToRegisterPage();
        registerPage.typeUsername(uniqueUsername);
        registerPage.typePassword("qwertyuii");
        registerPage.typeConfirmPassword("qwertyuii");
        registerPage.clickRegister();

        WebElement result = this.driver.findElement(By.xpath("//*[@id=\"app\"]/div/header/div[1]/div/div/div[2]/div/span"));
        Assert.assertEquals(result.getText(), "Welcome, " + uniqueUsername + "!");
    }

    @Test
    public void testRegistration_usernameAlreadyTaken_registrationFailed_11(){ //not work

        String username = UUID.randomUUID().toString();

        for(int i = 0; i < 2; i++){

           // setUp();

            RegisterPage registerPage = new RegisterPage(driver);

            registerPage.navigateToRegisterPage();
            registerPage.navigateToRegisterPage();
            registerPage.register(username, "123456", "");
            registerPage.logout();
            registerPage.navigateToHomePage();

            this.driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        }

        WebElement result = this.driver.findElement(By.id("error-box"));
        Assert.assertEquals(result.getText(), "madafaka");
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
