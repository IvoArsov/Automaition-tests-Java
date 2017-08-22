import com.sun.jna.platform.win32.Guid;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FirstTestCase {

    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\Marionette\\geckodriver.exe");
        this.driver = new FirefoxDriver();
        this.driver.get("http://demoqa.com/");
        this.driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    private void registerUserWithAllAttrs(String firstName, String lastName, String username, String email){
        this.driver.get("http://demoqa.com/registration/");
        WebElement firstNameField = this.driver.findElement(By.xpath("//*[@id=\"name_3_firstname\"]"));
        firstNameField.sendKeys(firstName);
        WebElement lastNameField = this.driver.findElement(By.xpath("//*[@id=\"name_3_lastname\"]"));
        lastNameField.sendKeys(lastName);

        WebElement radioBtnSingle = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[2]/div/div/input[1]"));
        radioBtnSingle.click();
        WebElement checkBoxReading = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[2]"));
        checkBoxReading.click();

        WebElement countryField = this.driver.findElement(By.xpath("//*[@id=\"dropdown_7\"]"));
        Select countrySelect = new Select(countryField);
        countrySelect.selectByValue("Bulgaria");

        WebElement monthField = this.driver.findElement(By.xpath("//*[@id=\"mm_date_8\"]"));
        Select monthSelect = new Select(monthField);
        monthSelect.selectByValue("5");

        WebElement dayField = this.driver.findElement(By.xpath("//*[@id=\"dd_date_8\"]"));
        Select daySelect = new Select(dayField);
        daySelect.selectByValue("22");

        WebElement yearField = this.driver.findElement(By.xpath("//*[@id=\"yy_date_8\"]"));
        Select yearSelect = new Select(yearField);
        yearSelect.selectByValue("1963");

        WebElement phoneField = this.driver.findElement(By.id("phone_9"));
        phoneField.sendKeys("0888888888");

        WebElement usernameField = this.driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        WebElement emailField = this.driver.findElement(By.id("email_1"));
        emailField.sendKeys(email);

        WebElement photoField = this.driver.findElement(By.id("profile_pic_10"));
        photoField.sendKeys("E:\\pix\\3.jpg");

        WebElement aboutField = this.driver.findElement(By.id("description"));
        aboutField.sendKeys(" dr br dr br dr br dr br dr br dr br");

        WebElement passwordField = this.driver.findElement(By.id("password_2"));
        passwordField.sendKeys("ssss8576AZIP");

        WebElement confirmPasswordField = this.driver.findElement(By.id("confirm_password_password_2"));
        confirmPasswordField.sendKeys("ssss8576AZIP");

        WebElement submitBtn = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[14]/div/input"));
        submitBtn.click();
    }

    @Test
    public void testRegistration_validCredentials_registrationSuccess(){

        String firstName = UUID.randomUUID().toString();
        String lastName = UUID.randomUUID().toString();
        String userName = UUID.randomUUID().toString();
        String email = UUID.randomUUID().toString() + "@gmail.com";

        registerUserWithAllAttrs(firstName, lastName, userName, email);

        WebElement notification = this.driver.findElement(By.id("post-49"));

       Assert.assertTrue(notification.isDisplayed());
    }

    @Test
    public void testRegistration_dublicatedUsername_registrationFailed(){
        String firstName1 = UUID.randomUUID().toString();
        String lastName1 = UUID.randomUUID().toString();
        String userName = UUID.randomUUID().toString();
        String email1 = UUID.randomUUID().toString() + "@gmail.com";

        registerUserWithAllAttrs(firstName1, lastName1, userName, email1);

        //second user

        String firstName2 = UUID.randomUUID().toString();
        String lastName2 = UUID.randomUUID().toString();

        String email2 = UUID.randomUUID().toString() + "@gmail.com";

        registerUserWithAllAttrs(firstName2, lastName2, userName, email2);

        WebElement notification = this.driver.findElement(By.xpath("//*[@id=\"post-49\"]/div/p/strong"));

        System.out.println(notification.getText());
    }

    //@After
    //public void tearDown(){
    //    this.driver.quit();
    //}

}
