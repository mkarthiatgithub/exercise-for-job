package runsupport;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public WebDriver driver;
    public DriverFactory driveFact;
    private Logger log = Logger.getLogger(Hooks.class);

    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests. Maximize the browser window and
     * set the selenium implicit wait.
     */

    @Before
    public void openBrowser(Scenario scenario) throws MalformedURLException {
        if (isWeb(scenario)) { // Only ramp up a browser if we are testing web
            driveFact = new DriverFactory();
            driver = driveFact.getDriver();
            log.info("@Before hook will run before the actual scenario");
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driveFact.setImplicitWait(60); // Default value for rest of run
            driver.manage().timeouts().implicitlyWait(driveFact.getImplicitWait(), TimeUnit.SECONDS);
        }
    }


    @After
    /**
     * Destroy the driver if tag is UI
     */
    public void embedScreenshot(Scenario scenario) {
        if (isWeb(scenario)) {
            new DriverFactory().destroyDriver();
            log.info("@After hook will run after the scenario is finished, even if the scenario failed");

        }

    }

    private boolean isWeb(Scenario scenario) {
        boolean isWeb = false;
        List<String> scenario_tag = (List<String>) scenario.getSourceTagNames();
        for (String aTag : scenario_tag) {
            System.out.println(aTag);
            if (aTag.equals("@ui")) { // API tests by pass the UI
                isWeb = true;
            }
        }
        return isWeb;
    }

}