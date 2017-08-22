package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    By emailField = By.xpath("//*[@id=\"loginfrm\"]/div[4]/div/div[1]/input");
    By passwordField = By.xpath("//*[@id=\"loginfrm\"]/div[4]/div/div[2]/input");
    By rememberMeCheck = By.id("remember-me");
    By loginBtn = By.xpath("//*[@id=\"loginfrm\"]/div[4]/button");
    By signUpBtn = By.xpath("//*[@id=\"loginfrm\"]/div[5]/div[1]/a");
    By forgetPasswordBtn = By.xpath("//*[@id=\"loginfrm\"]/div[5]/div[2]/a");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void typeEmail(String email){
        this.driver.findElement(emailField).sendKeys(email);
    }

    public void typePassword(String password){
        this.driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin(){
        this.driver.findElement(loginBtn).click();
    }

    public void clickRememberMe(){
        this.driver.findElement(rememberMeCheck).click();
    }

    public void clickSignUp(){
        this.driver.findElement(signUpBtn).click();
    }

    public void clickForgetPassword(){
        this.driver.findElement(forgetPasswordBtn).click();
    }

    public void directLogin(String email, String password){
        typeEmail(email);
        typePassword(password);
        clickRememberMe();
        clickLogin();
    }
}
