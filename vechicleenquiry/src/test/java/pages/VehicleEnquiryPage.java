package pages;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Entry point for the test to input Car details
 */
public class VehicleEnquiryPage {
    private Logger log = Logger.getLogger(VehicleEnquiryPage.class);
    private WebDriver driver;


    public String button = "#content > form > div > div > div.form-group.no-bottom > fieldset > button";
    //Locators
    @FindBy(how = How.CSS, using = "#Vrm")
    private WebElement regNo;

    @FindBy(how = How.CSS, using = "#content > form > div > div > div.form-group.no-bottom > fieldset > button")
    private WebElement continueButton;

    public VehicleEnquiryPage(WebDriver driver) {
        this.driver = driver;
        log.info("Entering the car details");
        PageFactory.initElements(driver, this);
    }

    public void enterRegNumber(String regNumber) {
        regNo.sendKeys(regNumber);
    }

    public void clickContinue() {
        continueButton.click();
    }
}
