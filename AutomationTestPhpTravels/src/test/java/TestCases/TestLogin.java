package TestCases;

import Pages.LoginPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestLogin {

    private WebDriver driver;

    private static final String CONST_EMAIL = "user@phptravels.com";
    private static final String CONST_PASSWORD = "demouser";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        this.driver.get("http://phptravels.net/login");
        this.driver.manage().window().maximize();
    }

    @Test
    public void testLogin_validCredentials_loginSuccessful_2(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.directLogin(CONST_EMAIL, CONST_PASSWORD);

        WebElement result = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/h3"));
        Assert.assertEquals(result.getText(), "Hi, John Smith");
    }

    @Test
    public void testLogin_invalidPassword_loginFailed_3(){
        LoginPage loginPage = new LoginPage(driver);

        String invalidPassword = UUID.randomUUID().toString();
        loginPage.directLogin(CONST_EMAIL, invalidPassword);

        WebElement result = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[1]/div"));
        Assert.assertEquals(result.getText(), "Invalid Email or Password");
    }

    @Test
    public void testLogin_invalidEmail_loginFailed_4(){
        LoginPage loginPage = new LoginPage(driver);

        String invalidEmail = UUID.randomUUID().toString() + "@gmail.com";
        loginPage.directLogin(invalidEmail, CONST_PASSWORD);

        WebElement result = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[1]/div"));
        Assert.assertEquals(result.getText(), "Invalid Email or Password");
    }

    @Test
    public void testLogin_typeOnlyEmail_loginFailed_5(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.directLogin(CONST_EMAIL, "");
        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @Test
    public void testLogin_typeOnlyPassword_loginFailed_6(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.directLogin("", CONST_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @Test
    public void testLogin_typeInvalidFormatEmail_loginFailed_7(){
        LoginPage loginPage = new LoginPage(driver);

        String invalidFormatEmail = UUID.randomUUID().toString() + ".gmail.com";
        loginPage.directLogin(invalidFormatEmail, CONST_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @Test
    public void testLogin_withoutRememberMe_dontSaveSession_8(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeEmail(CONST_EMAIL);
        loginPage.typePassword(CONST_PASSWORD);
        loginPage.clickLogin();

        driver.quit();

        setUp();
        driver.get("http://phptravels.net/account/");

        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @Test
    public void testLogin_clickForgetPassword_mustShowForm_9(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickForgetPassword();

        WebElement result = driver.findElement(By.xpath("//*[@id=\"ForgetPassword\"]/div/div/div[1]/h4"));
        Assert.assertEquals(result.getText(), "Forget Password");
    }

    @Test
    public void testLogin_clickSignUp_mustRedirectToRegisterPage_10(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickSignUp();

        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/register");
    }

    @Test
    public void testLogin_typeCredentialsRefreshPage_fieldMustBeEmpty_11(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeEmail(CONST_EMAIL);
        loginPage.typePassword(CONST_PASSWORD);

        driver.get(driver.getCurrentUrl());

        WebElement emailFieldResult = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[4]/div/div[1]/input"));
        WebElement passwordFieldResult = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[4]/div/div[2]/input"));

        Boolean isTrue = false;
        if(emailFieldResult.getText().equals("") && passwordFieldResult.getText().equals("")){
            isTrue = true;
       }

       Assert.assertTrue(isTrue);
    }

    @Test
    public void testLogin_loginAndRedirectToHomePage_mustStayLogged_12() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.directLogin(CONST_EMAIL, CONST_PASSWORD);
        Thread.sleep(2000);

        // click at logo, redirect to Home Page
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/a/img")).click();

        WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/ul[2]/li[2]/a"));
        Assert.assertEquals(result.getText(), "John");
    }

    @Test
    public void testLogin_trySqlAttackOnPasswordField_loginFailed_15(){
        LoginPage loginPage = new LoginPage(driver);

        String sqlAttackPassword = "' OR '1'='1";

        loginPage.directLogin(CONST_EMAIL, sqlAttackPassword);
        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @Test
    public void testLogin_trySqlAttackOnEmailField_loginFailed_16(){
        LoginPage loginPage = new LoginPage(driver);

        String sqlAttackEmail = "' OR '1'='1";

        loginPage.directLogin(sqlAttackEmail, CONST_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @Test
    public void testLogin_tryXssAttackOnEmailField_loginFailed_17(){
        LoginPage loginPage = new LoginPage(driver);

        String xssAttackEmail = "<script>alert(document.cookie);</script>";

        loginPage.directLogin(xssAttackEmail, CONST_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @Test
    public void testLogin_tryXssAttackOnPasswordField_loginFailed_18(){
        LoginPage loginPage = new LoginPage(driver);

        String xssAttackPassword = "<script>alert(document.cookie);</script>";

        loginPage.directLogin(CONST_EMAIL, xssAttackPassword);
        Assert.assertEquals(driver.getCurrentUrl(), "http://phptravels.net/login");
    }

    @After
    public void tearDown(){
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
    }
}
