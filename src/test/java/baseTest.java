import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


public class baseTest {
    static WebDriver driver;
    static Actions action;
    final static Logger logger = Logger.getLogger(baseTest.class);
    protected JavascriptExecutor js;

    @Before
    public void setUp() throws Exception{

        logger.info("Test Başladı");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        Select select;
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.get("https://www.network.com.tr/");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Thread.sleep(1000);
    }

    //@After
    public void quit(){
        logger.info("Test Bitti");

        driver.quit();
    };


    public static WebElement findElement (By by){
        return driver.findElement(by);
    }


    public static void clickElement(By by){
        findElement(by).click();
        logger.info(by + "elementine tiklandi");
    }

    public static void sendElement(By by, String text){
        findElement(by).sendKeys(text);
        logger.info(by + "elementine" + text + " gönderildi.");
    }
    public static void clickEnter(By by, Keys key){
        findElement(by).sendKeys(Keys.ENTER);
    }

    public void assertControl(By assertName, String expectedName){
        String assertName1 = findElement(assertName).getText();
        Assert.assertEquals(assertName1,expectedName);
        logger.info(assertName + " === " + expectedName);
    }

    public void assertName(String actualName, String expectedName){
        Assert.assertEquals(actualName,expectedName);
        logger.info("actual name = "+ actualName + " = " + "expected name = " + expectedName);
    }

    public void hoverElement(By by) {

        action.moveToElement(findElement(by)).build().perform();
    }

    public boolean displayElement(By by) {
        try {
            return findElement(by).isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    public void clickListElement(By by, int index){
        findElements(by).get(index).click();
        logger.info(by + "elementine tiklandi");
    }
    public String getText(By by){
        return findElement(by).getText();
    }


    public boolean displayListElement(By by, int index) {
        try {
            return findElements(by).get(index).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void scrollElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",findElement(by) );
    }

    public void scroll(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,17500)");
    }


    public void selectByValue (By by, String key){
        getSelect(by).selectByValue(key);
    }

    public Select getSelect(By by){
        return new Select(findElement(by));
    }
}