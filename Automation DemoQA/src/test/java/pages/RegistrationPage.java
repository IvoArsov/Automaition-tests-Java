package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RegistrationPage {

    WebDriver driver;

    By firstName = By.id("name_3_firstname");
    By lastName = By.id("name_3_lastname");
    By submitBtn = By.name("pie_submit");
    By selectCountry = By.id("dropdown_7");
    By selectMonth = By.id("mm_date_8");
    By selectDay = By.id("dd_date_8");
    By selectYear = By.id("yy_date_8");
    By phoneNumber = By.id("phone_9");
    By username = By.id("username");
    By email = By.id("email_1");
    By profilePicture = By.id("profile_pic_10");
    By userAbout = By.id("description");
    By password = By.id("password_2");
    By confirmPassword = By.id("confirm_password_password_2");

    public RegistrationPage(WebDriver driver){ // constructor
        this.driver = driver;
    }

    // METHODS
    public void goToRegister(){
        this.driver.get("http://demoqa.com/registration/");
    }

    public void typeFirstName(String firstNameId){
        driver.findElement(firstName).sendKeys(firstNameId);
    }

    public void typeLastName(String lastNameId){
        driver.findElement(lastName).sendKeys(lastNameId);
    }

    public void checkHobby(String hobbyNum){

        if(hobbyNum.equals("1")){
           WebElement checkBox = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[1]"));
           checkBox.click();
        }
        if(hobbyNum.equals("2")){
            WebElement checkBox = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[2]"));
            checkBox.click();
        }
        if(hobbyNum.equals("3")){
            WebElement checkBox = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[3]"));
            checkBox.click();
        }
    }

    public void maritalStatus(String MaritalNum){

        if(MaritalNum.equals("1")){
            WebElement checkBox = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[2]/div/div/input[1]"));
            checkBox.click();
        }
        if(MaritalNum.equals("2")){
            WebElement checkBox = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[2]/div/div/input[2]"));
            checkBox.click();
        }
        if(MaritalNum.equals("3")){
            WebElement checkBox = this.driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[2]/div/div/input[3]"));
            checkBox.click();
        }
    }

    public void selectCountry(String country){
        WebElement countrySelector = this.driver.findElement(selectCountry);
        Select countrySelect = new Select(countrySelector);
        countrySelect.selectByValue(country);
    }

    public void selectMonth(String birthMonth){
        WebElement monthSelector = this.driver.findElement(selectMonth);
        Select monthSelect = new Select(monthSelector);
        monthSelect.selectByValue(birthMonth);
    }

    public void selectDay(String birthDay){
        WebElement daySelector = this.driver.findElement(selectDay);
        Select daySelect = new Select(daySelector);
        daySelect.selectByValue(birthDay);
    }

    public void selectYear(String birthYear){
        WebElement yearSelector = this.driver.findElement(selectYear);
        Select yearSelect = new Select(yearSelector);
        yearSelect.selectByValue(birthYear);
    }

    public void typePhoneNumber(String number){
        this.driver.findElement(phoneNumber).sendKeys(number);
    }

    public void typeUsername(String user){
        this.driver.findElement(username).sendKeys(user);
    }

    public void typeEmail(String emailAddress){
        this.driver.findElement(email).sendKeys(emailAddress);
    }

    public void typeAbout(String about){
        this.driver.findElement(userAbout).sendKeys(about);
    }

    public void setProfilePicture(String pictureUrl){
        this.driver.findElement(profilePicture).sendKeys(pictureUrl);
    }

    public void typePassword(String pass){
        this.driver.findElement(password).sendKeys(pass);
    }

    public void typeConfirmPassword(String confirmPass){
        this.driver.findElement(confirmPassword).sendKeys(confirmPass);
    }

    public void clickSubmit(){
        driver.findElement(submitBtn).click();
    }

    public void registerUser(String firstName, String hobbyCheck,String lastName, String phoneNumber, String username,
                             String email, String password){
        typeFirstName(firstName);
        typeLastName(lastName);
        checkHobby(hobbyCheck);
        typePhoneNumber(phoneNumber);
        typeUsername(username);
        typeEmail(email);
        typePassword(password);
        typeConfirmPassword(password);

        clickSubmit();
    }
}
