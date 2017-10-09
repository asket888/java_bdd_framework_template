package steps.setup;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static utils.PropertyGetterUtil.getPropertyValue;

public class BaseDriver {

    private final static int TIMEOUT_SECONDS = parseInt(getPropertyValue("webdriver.default.timeout.sec"));
    protected static WebDriver driver;
    protected static WebDriverWait driverWait;
    protected static Actions driverActions;
    protected static JavascriptExecutor driverJsExecutor;

    @BeforeClass
    public static void initSelenium(){
        getDriver();
    }

    @AfterClass
    public static void closeSelenium(){
        closeDriver();
    }

    private static void setupDriver(String browserToUse){

        driverWait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        driverActions = new Actions(driver);
        driverJsExecutor = (JavascriptExecutor) driver;

        if (browserToUse.equals("IE")) {
            System.setProperty("webdriver.ie.driver",
                    System.getProperty("user.dir") + "\\libs\\browser\\IEDriverServer.exe");
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability("ignoreZoomSetting", true);
            caps.setCapability("ignoreProtectedModeSettings", true);
            caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
            caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            driver = new InternetExplorerDriver(caps);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else if (browserToUse.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\libs\\browser\\chromedriver.exe");
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("enableElementCacheCleanup", true);
            caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            driver = new ChromeDriver(caps);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else {
            Assert.fail("No proper browser name steps.setup in config.properties file");
        }
    }

    public static WebDriver getDriver() {

        if (driver == null) {
            setupDriver(getPropertyValue("webdriver.browser"));
        }
        return driver;
    }

    public static void closeDriver(){
        try {
            driver.quit();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally{
            driver = null;
        }
    }
}
