package runsupport;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.PropertyReader;

public class DriverFactory {
    protected static WebDriver driver;
    private static long implicitWaitTimeInSeconds;

    private Logger log = Logger.getLogger(DriverFactory.class);

    public DriverFactory() {
        initialize();
    }

    private void initialize() {
        if (driver == null)
            createNewDriverInstance();
    }

    private void createNewDriverInstance() {

        PropertyReader propReader = new PropertyReader();
        String browser = propReader.readProperty("browser");
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Karthi-Job\\dvla-tests\\pageElement\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            log.error(propReader.propertyNotValidMsg("browser", browser));
        }
        Assert.assertNotNull("Driver failed initialization", driver);
    }

    WebDriver getDriver() {
        return driver;
    }

    void destroyDriver() {
        driver.quit();
        driver = null;
    }

    long getImplicitWait() {
        return implicitWaitTimeInSeconds;
    }

    void setImplicitWait(long waitTime) {
        DriverFactory.implicitWaitTimeInSeconds = waitTime;
    }
}
