package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPageFactory {

    WebDriver driver;

    public RegistrationPageFactory(WebDriver driver){ //ctor
        this.driver = driver;
    }
        //TODO:HOW
    @FindBy(id = "name_3_firstname")
    WebElement firstNameField;

    @FindBy(how = How.ID, using = "name_3_lastname")
    WebElement lastNameField;

    @FindBy(xpath = "//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[1]")
    WebElement hobbyDanceCheck;

    @FindBy(id = "phone_9")
    WebElement phoneNumberField;

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "email_1")
    WebElement emailField;

    @FindBy(id = "password_2")
    WebElement passwordField;

    @FindBy(id = "confirm_password_password_2")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//*[@id=\"pie_register\"]/li[14]/div/input")
    WebElement submitBtn;

    //TODO: Finish method
    public void register(String firstName,String lastName, String hobbyCheck, String phoneNumber,
                         String uniqueUsername, String email, String pass){

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);

        if(hobbyCheck.equals("1")){
            WebElement check = driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[1]"));
            check.click();
        }else if(hobbyCheck.equals("2")){
            WebElement check = driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[2]"));
            check.click();
        }else if(hobbyCheck.equals("3")){
            WebElement check = driver.findElement(By.xpath("//*[@id=\"pie_register\"]/li[3]/div/div[1]/input[3]"));
            check.click();
        }

        phoneNumberField.sendKeys(phoneNumber);
        usernameField.sendKeys(uniqueUsername);
        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        confirmPasswordField.sendKeys(pass);
        submitBtn.click();
    }
}
