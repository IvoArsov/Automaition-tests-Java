package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    WebDriver driver;

    By usernameField = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[1]/div/input");
    By passwordField = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[2]/div/input");
    By confirmPasswordField = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[3]/div/input");
    By emailField = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[4]/div/input");
    By registerBtn = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/form/fieldset/div[5]/div/button");
    By registerLink = By.xpath("//*[@id=\"app\"]/div/header/div[1]/div/div/div[2]/div/a[3]");
    By logoutLink = By.xpath("//*[@id=\"app\"]/div/header/div[1]/div/div/div[2]/div/a[4]");
    By homeLink = By.xpath("//*[@id=\"app\"]/div/header/div[1]/div/div/div[2]/div/a[1]");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToRegisterPage(){
        this.driver.findElement(registerLink).click();
    }

    public void navigateToHomePage(){
        this.driver.findElement(homeLink).click();
    }

    public void logout(){
        this.driver.findElement(logoutLink).click();
    }

    public void typeUsername(String username){
        this.driver.findElement(usernameField).sendKeys(username);
    }

    public void typePassword(String password){
        this.driver.findElement(passwordField).sendKeys(password);
    }

    public void typeConfirmPassword(String confirmPassword){
        this.driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void typeEmail(String email){
        this.driver.findElement(emailField).sendKeys(email);
    }

    public void clickRegister(){
        this.driver.findElement(registerBtn).click();
    }

    public void register(String username, String password, String email){
        typeUsername(username);
        typePassword(password);
        typeConfirmPassword(password);
        typeEmail(email);
        clickRegister();
    }
}
