package testCases;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegistrationPage;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestRegistration {

    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("http://demoqa.com");
        this.driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testRegistration_allFieldsFilledWithValidCredential_RegistrationSuccess(){

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.goToRegister();
        registrationPage.typeFirstName("Ivo");
        registrationPage.typeLastName("Arsov");
        registrationPage.checkHobby("2");
        registrationPage.maritalStatus("3");
        registrationPage.selectCountry("Algeria");
        registrationPage.selectMonth("12");
        registrationPage.selectDay("22");
        registrationPage.selectYear("1987");
        registrationPage.typePhoneNumber("0888888888");
        registrationPage.typeAbout("dr br dr br dr br dr br dr br dr br dr br dr br dr br dr br dr br");

        String uniqueUsername = UUID.randomUUID().toString();
        registrationPage.typeUsername(uniqueUsername);

        String uniqueEmail = UUID.randomUUID().toString() + "@gmail.com";
        registrationPage.typeEmail(uniqueEmail);

        registrationPage.setProfilePicture("E:\\pix\\0a1.jpg");
        registrationPage.typePassword("1234567890");
        registrationPage.typeConfirmPassword("1234567890");

        registrationPage.clickSubmit();

        WebElement result = this.driver.findElement(By.xpath("//*[@id=\"post-49\"]/dv/p"));
        Assert.assertEquals(result.getText(), "Thank you for your registration");

    }
                //TODO:Unique username, email
    @Test
    public void testRegistration_usernameAlreadyTaken_registrationFailed(){

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.goToRegister();
        String username = UUID.randomUUID().toString();
        String oldEmail = UUID.randomUUID().toString() + "@gmail.com";
        String newEmail = UUID.randomUUID().toString() + "@abv.com";
        registrationPage.registerUser("ivan", "2", "petrov","088888888888",
                username, oldEmail, "123456789000");

        this.driver.get("http://demoqa.com/registration/");

        registrationPage.typeFirstName("Peter");
        registrationPage.typeLastName("ivanov");
        registrationPage.checkHobby("1");
        registrationPage.maritalStatus("2");
        registrationPage.selectCountry("Bulgaria");
        registrationPage.selectMonth("5");
        registrationPage.selectDay("31");
        registrationPage.selectYear("1976");
        registrationPage.typePhoneNumber("08888887688");
        registrationPage.typeUsername(username);
        registrationPage.typeEmail(newEmail);
        registrationPage.setProfilePicture("E:\\pix\\0a1.jpg");
        registrationPage.typeAbout("0gttggttggttggttggttggttggttggttggttggttggttggttggttggttggttggttggttggttg");
        registrationPage.typePassword("1234567890");
        registrationPage.typeConfirmPassword("1234567890");

        registrationPage.clickSubmit();


        WebElement result = this.driver.findElement(By.xpath("//*[@id=\"post-49\"]/div/p"));
        Assert.assertEquals(result.getText(), "Error: Username already exists");
    }

    @Test
    public void testRegistration_emailAlreadyTaken_registrationFailed(){

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.goToRegister();
        String oldUsername = UUID.randomUUID().toString();
        String newUsername = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString() + "@gmail.com";

        registrationPage.registerUser("ivan", "2", "petrov","088888888888",
                oldUsername, email, "123456789000");

        this.driver.get("http://demoqa.com/registration/");

        registrationPage.typeFirstName("Peter");
        registrationPage.typeLastName("ivanov");
        registrationPage.checkHobby("1");
        registrationPage.maritalStatus("2");
        registrationPage.selectCountry("Bulgaria");
        registrationPage.selectMonth("5");
        registrationPage.selectDay("31");
        registrationPage.selectYear("1976");
        registrationPage.typePhoneNumber("08888887688");
        registrationPage.typeUsername(newUsername);
        registrationPage.typeEmail(email);
        registrationPage.setProfilePicture("E:\\pix\\0a1.jpg");
        registrationPage.typeAbout("0gttggttggttggttggttggttggttggttggttggttggttggttggttggttggttggttggttggttg");
        registrationPage.typePassword("1234567890");
        registrationPage.typeConfirmPassword("1234567890");

        registrationPage.clickSubmit();


        WebElement result = this.driver.findElement(By.xpath("//*[@id=\"post-49\"]/div/p"));
        Assert.assertEquals(result.getText(), "Error: E-mail address already exists");
    }

    @Test
    public void testRegister_withoutUsernameWithOnlyReqFields_registrationFailed(){
        RegistrationPage registrationPage = new RegistrationPage(driver);

        String email = UUID.randomUUID().toString() + "@gmail.com";

        registrationPage.goToRegister();
        registrationPage.typeFirstName("Qna");
        registrationPage.typeLastName("popowa");
        registrationPage.checkHobby("2");
        registrationPage.typePhoneNumber("0888888888888");
        registrationPage.typeEmail(email);
        registrationPage.typePassword("qwertyuiopsd");
        registrationPage.typeConfirmPassword("qwertyuiopsd");
        registrationPage.clickSubmit();

        WebElement result = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[7]/div/div/span"));
        Assert.assertEquals(result.getText(), "* This field is required");
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
