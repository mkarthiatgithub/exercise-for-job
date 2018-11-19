package pages;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * This is Page Factory Initialisation
 */
public class GetVehicleInfoPage {
    Logger log = Logger.getLogger(GetVehicleInfoPage.class);
    //Locators
    @FindBy(how = How.CSS, using = "#get-started > a")
    private WebElement startNowButton;

    public GetVehicleInfoPage(WebDriver driver) {
        log.info("Starting the page and initializing");
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void clickOnStartButton() {
        startNowButton.click();
    }
}

