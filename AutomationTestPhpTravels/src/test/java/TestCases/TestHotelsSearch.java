package TestCases;

import Pages.HotelsSearchPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestHotelsSearch {

    private WebDriver driver;

    private static final String CONST_LOCATION = "Sofia";
    private static final String CONST_ADULT_NUMBER = "3";
    private static final String CONST_CHILD_NUMBER = "0";
    private static final String CONST_CHECKIN_DATE = "02/10/2017";
    private static final String CONST_CHECKOUT_DATE = "02/15/2017";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("http://phptravels.net/");
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void testHotelSearch_allValid_searchSuccessful_21(){

        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);

        hotelsSearchPage.directSearch(CONST_LOCATION, CONST_CHECKIN_DATE, CONST_CHECKOUT_DATE, CONST_ADULT_NUMBER, CONST_CHILD_NUMBER);

        WebElement locationResult = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/span"));
        WebElement checkInDateResult = driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/span"));
        WebElement checkOutDateResult = driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/span"));
        WebElement adultNumberResult = driver.findElement(By.xpath("/html/body/div[3]/div/div[9]/span"));
        WebElement childNumberResult = driver.findElement(By.xpath("/html/body/div[3]/div/div[10]/span"));

        Assert.assertEquals(locationResult.getText(), CONST_LOCATION);
        Assert.assertEquals(checkInDateResult.getText(), CONST_CHECKIN_DATE);
        Assert.assertEquals(checkOutDateResult.getText(), CONST_CHECKOUT_DATE);
        Assert.assertEquals(adultNumberResult.getText(), CONST_ADULT_NUMBER);
        Assert.assertEquals(childNumberResult.getText(), CONST_CHILD_NUMBER);
    }

    @Test
    public void testHotelSearch_invalidLocation_searchFailed_22(){

        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);

        String location = UUID.randomUUID().toString();

        hotelsSearchPage.directSearch(location, CONST_CHECKIN_DATE, CONST_CHECKOUT_DATE, CONST_ADULT_NUMBER, CONST_CHILD_NUMBER);

        WebElement result = this.driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/h1"));
        Assert.assertEquals(result.getText(), "No Results!!");
    }

    @Test
    public void testHotelSearch_withoutLocation_stayOnThisPageUrlNotChange_23(){

        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);

        hotelsSearchPage.directSearch("", CONST_CHECKIN_DATE, CONST_CHECKOUT_DATE, CONST_ADULT_NUMBER, CONST_CHILD_NUMBER);

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "http://phptravels.net/");
    }

    @Test
    public void testHotelSearch_onlyLocation_correctSearch_24(){

        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);

        hotelsSearchPage.typeLocation(CONST_LOCATION);
        hotelsSearchPage.clickSearch();

        WebElement locationResult = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/span"));
        Assert.assertEquals(locationResult.getText(), CONST_LOCATION);
    }

    @Test
    public void testHotelSearch_invalidCheckInDate_mustShowNoResultMsg_25(){

        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);

        String incorrectCheckInDate = "14/25/2017";

        hotelsSearchPage.directSearch(CONST_LOCATION, incorrectCheckInDate, CONST_CHECKOUT_DATE, CONST_ADULT_NUMBER, CONST_CHILD_NUMBER);

        WebElement result = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/h1"));
        Assert.assertEquals(result.getText(), "No Results!!");
    }

    @Test
    public void testHotelSearch_InvalidCheckOutDate_mustShowNoResultMsg_26(){

        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);

        String incorrectCheckOutDate = "14/25/2017";

        hotelsSearchPage.directSearch(CONST_LOCATION, CONST_CHECKIN_DATE, incorrectCheckOutDate, CONST_ADULT_NUMBER, CONST_CHILD_NUMBER);

        WebElement result = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/h1"));
        Assert.assertEquals(result.getText(), "No Results!!");
    }


    @After
    public void tearDown(){
        this.driver.quit();
    }
}
//TODO: SearchResultPage HotelProfilePage BookPage