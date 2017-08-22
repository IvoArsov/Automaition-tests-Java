package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HotelsSearchPage {

    WebDriver driver;

    By locationField = By.id("citiesInput");
    By checkInField = By.xpath("//*[@id=\"dpean1\"]/div/input");
    By checkOutField = By.xpath("//*[@id=\"dpd2\"]/div/input");
    By adultSelector = By.id("adults");
    By childSelector = By.id("child");
    By searchBtn = By.xpath("//*[@id=\"EXPEDIA\"]/div/form/div[6]/div/button");

    public  HotelsSearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void typeLocation(String location){
        this.driver.findElement(locationField).sendKeys(location);
    }

    public void typeCheckInDate(String checkInDate){
        WebElement checkIn = this.driver.findElement(checkInField);
        checkIn.clear();
        checkIn.sendKeys(checkInDate);
    }

    public void typeCheckOutDate(String checkOutDate){
        WebElement checkOut = this.driver.findElement(checkOutField);
        checkOut.clear();
        checkOut.sendKeys(checkOutDate);
    }

    public void selectAdultNumber(String adultNumber){
        Select adultNumberSelector = new Select(this.driver.findElement(adultSelector));
        adultNumberSelector.selectByValue(adultNumber);
    }

    public void selectChildNumber(String childNumber){
        Select childNumberSelector = new Select(this.driver.findElement(childSelector));
        childNumberSelector.selectByValue(childNumber);
    }

    public void directSearch(String location, String checkInDate, String checkOutData,
                             String adultNumber, String childNumber){
        typeLocation(location);
        typeCheckInDate(checkInDate);
        typeCheckOutDate(checkOutData);
        selectAdultNumber(adultNumber);
        selectChildNumber(childNumber);

        clickSearch();
    }

    public void clickSearch(){
        this.driver.findElement(searchBtn).click();
    }
}
