package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import pages.GetVehicleInfoPage;
import pages.VehicleDetailsPage;
import pages.VehicleEnquiryPage;
import runsupport.DriverFactory;

import java.util.List;

@ScenarioScoped
public class DVLAVechicleSteps extends DriverFactory {
    protected static Logger log = Logger.getLogger(DVLAVechicleSteps.class);
    List<VehicleDetail> vechicleDetails;
    VehicleEnquiryPage vehicleEnquiryPage;
    VehicleDetailsPage vehicleDetailsPage;
    private FileService csvService;
    private FileService excelService;
    private String PAGE_URL = "https://www.gov.uk/get-vehicle-information-from-dvla";
    @Inject
    public DVLAVechicleSteps(CSVReader csvReader,
                             ExcelReader excelReader) {
        this.csvService = csvReader;
        this.excelService = excelReader;
    }

    @Given("^Am on DVLA Site$")
    public void amOnDVLASite() {
        log.info("Entering the site");
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        driver.manage().window().maximize();

        GetVehicleInfoPage getVehicleInfoPage = new GetVehicleInfoPage(driver);
        getVehicleInfoPage.clickOnStartButton();

        vehicleEnquiryPage = new VehicleEnquiryPage(driver);
        vehicleDetailsPage = new VehicleDetailsPage(driver);
    }

    @When("^I process all (.*) files in the folder (.*) and get their details$")
    public void iProcessAllCSVFilesInTheFolderAndGetTheirDetails(String mime, String dirName) {
        log.info("getting ddetails of the car");
        if (mime.equalsIgnoreCase(SupportedMIME.CSV.toString())) {
            vechicleDetails = csvService.getVechicleDetails(dirName);
        } else if (mime.equalsIgnoreCase(SupportedMIME.XLSX.toString())) {
            vechicleDetails = excelService.getVechicleDetails(dirName);
        } else {
            throw new RuntimeException("Unsupported MIME in test");
        }

    }

    @Then("^I assert the car Make,color for a given regNumber$")
    public void iAssertTheCarMakeColorForAGivenRegNumber() {
        log.info("Verifcaiton in process");
        for (VehicleDetail singleCar : vechicleDetails) {
            vehicleEnquiryPage.enterRegNumber(singleCar.getRegNumber());
            vehicleEnquiryPage.clickContinue();
            vehicleDetailsPage.validate(singleCar.getMake(), singleCar.getColor());
            vehicleDetailsPage.searchAgain();
        }

    }
}
