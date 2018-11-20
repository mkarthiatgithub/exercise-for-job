package pages;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

/**
 * This page fetches the details of the car under test
 */
public class VehicleDetailsPage {
    Logger log = Logger.getLogger(VehicleDetailsPage.class);
    private WebDriver driver;

    //Locators
    @FindBy(how = How.CSS, using = "#pr3 > div > ul > li:nth-child(2) > span:nth-child(2) > strong")
    private WebElement makeElement;

    @FindBy(how = How.CSS, using = "#pr3 > div > ul > li:nth-child(3) > span:nth-child(2) > strong")
    private WebElement colorElement;

    @FindBy(how = How.CSS, using = "#proposition-name")
    private WebElement searchAgain;

    public VehicleDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void validate(String make, String color) {
        assertEquals("Make doesn't match", make.toLowerCase(), makeElement.getText().toLowerCase());
        assertEquals("Color doesn't match", color.toLowerCase(), colorElement.getText().toLowerCase());
    }

    public void searchAgain() {
        searchAgain.click();
    }
}
