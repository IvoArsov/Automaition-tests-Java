package testCases;


import Helper.BrowserFactory;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.RegistrationPageFactory;

import java.util.UUID;

public class TestRegistrationFactory {

    @Test
    public void testRegistration_validCredentials_registrationSuccess(){

        WebDriver driver = BrowserFactory.startBrowser("chrome", "http://demoqa.com/registration/");

        RegistrationPageFactory registrationPageFactory = PageFactory.initElements(driver, RegistrationPageFactory.class);

        String uniqueUsername = UUID.randomUUID().toString();
        String uniqueEmail = UUID.randomUUID().toString() + "@gmail.com";

        registrationPageFactory.register("Ivan", "Pavlov","2", "08888888856",
                uniqueUsername, uniqueEmail, "123445bghyt");

        WebElement result = driver.findElement(By.xpath("//*[@id=\"post-49\"]/div/p"));
        Assert.assertEquals(result.getText(), "Thank you for your registration");

        driver.quit();
    }

    @Test
    public void testRegistration_usernameAlreadyTaken_registrationFailed(){

        WebDriver driver = BrowserFactory.startBrowser("chrome", "http://demoqa.com/registration/");
        RegistrationPageFactory regPage =PageFactory.initElements(driver, RegistrationPageFactory.class);

        String username = UUID.randomUUID().toString();
        String oldEmail = UUID.randomUUID().toString() + "@abv.bg";
        String newEmail = UUID.randomUUID().toString() + "@abv.bg";

        regPage.register("Ivan", "Pavlov","2", "08888888856",
                username, oldEmail, "123445bghyt");

        driver.get("http://demoqa.com/registration/");

        regPage.register("Ivan", "Pavlov","2", "08888888856",
                username, newEmail, "123445bghyt");

        WebElement result = driver.findElement(By.xpath("//*[@id=\"post-49\"]/div/p"));
        Assert.assertEquals(result.getText(), "Error: Username already exists");

        driver.quit();
    }
}
