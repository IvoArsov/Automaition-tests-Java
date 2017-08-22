package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver driver;

    By usernameField = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[1]/div/input");
    By passwordField = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[2]/div/input");
    By loginBtn = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[3]/div/button");
    By loginLink = By.xpath("//*[@id=\"app\"]/div/header/div[1]/div/div/div[2]/div/a[2]");

    public LoginPage(WebDriver driver){ //CONSTRUCTOR
        this.driver = driver;
    }

    //METHODS

    public void navigateToLoginPage(){
        this.driver.findElement(loginLink).click();
    }

    public void typeUsername(String username){
        this.driver.findElement(usernameField).sendKeys(username);
    }

    public void typePassword(String password){
        this.driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin(){
        this.driver.findElement(loginBtn).click();
    }

    public void login(String username, String password){
        typeUsername(username);
        typePassword(password);
        clickLogin();
    }
}
