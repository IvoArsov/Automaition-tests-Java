package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {

    WebDriver driver;

    By tableOfResults = By.xpath("/html/body/div[5]/div[3]/div/table");
    By firstResultLink = By.xpath("/html/body/div[5]/div[3]/div/table/tbody/tr[1]/td/div/div[1]/div/a/img");
    By secondResultLink = By.xpath("/html/body/div[5]/div[3]/div/table/tbody/tr[2]/td/div/div[1]/div/a/img");
    By thirdResultLink = By.xpath("/html/body/div[5]/div[3]/div/table/tbody/tr[3]/td/div/div[1]/div/a/img");

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement checkSearchResult(){
        List<WebElement> resultList = this.driver.findElements(tableOfResults);
        WebElement firstResult = resultList.get(0);
        return firstResult;
    }

    public void clickFirstResult(){
        this.driver.findElement(firstResultLink).click();
    }

    public void clickSecondResult(){
        this.driver.findElement(secondResultLink).click();
    }

    public void clickThirdResult(){
        this.driver.findElement(thirdResultLink).click();
    }
}
