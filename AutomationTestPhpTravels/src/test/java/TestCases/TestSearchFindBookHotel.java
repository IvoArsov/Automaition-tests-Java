package TestCases;

import Pages.HotelsSearchPage;
import Pages.SearchResultPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestSearchFindBookHotel {

    private WebDriver driver;

    private static final String CONST_LOCATION = "Sofia";
    private static final String CONST_ADULT_NUMBER = "3";
    private static final String CONST_CHILD_NUMBER = "0";
    private static final String CONST_CHECKIN_DATE = "02/10/2017"; //TODO: DateTime Now
    private static final String CONST_CHECKOUT_DATE = "02/15/2017"; //TODO: DateTime Now + 5 days

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        this.driver.get("http://phptravels.net/en");
        this.driver.manage().window().maximize();
    }


    @Test
    public void testSearchFindBook_allValid_success_28(){
        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);

        hotelsSearchPage.typeLocation(CONST_LOCATION);
        hotelsSearchPage.clickSearch();

        SearchResultPage searchResultPage = new SearchResultPage(driver);

        WebElement result = searchResultPage.checkSearchResult();

        Assert.assertTrue(result.isDisplayed());
    }
    //@After
    //public void tearDown(){
    //    this.driver.quit();
    //}
}
