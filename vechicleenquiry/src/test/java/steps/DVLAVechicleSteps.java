package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.runtime.java.guice.ScenarioScoped;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import pages.GetVehicleInfoPage;
import pages.VehicleDetailsPage;
import pages.VehicleEnquiryPage;
import runsupport.DriverFactory;

import java.util.ArrayList;
import java.util.List;

@ScenarioScoped
public class DVLAVechicleSteps extends DriverFactory {
    private ServiceReader serviceReader;
    private FileService service;

    @Inject
    public DVLAVechicleSteps(CSVReader fileService) {
        this.service = fileService;
    }

    private String PAGE_URL = "https://www.gov.uk/get-vehicle-information-from-dvla";

    protected static Logger log = Logger.getLogger(DVLAVechicleSteps.class);

    @Given("^Am on DVLA Site$")
    public void amOnDVLASite() {

        List<VehicleDetail> vechicleDetails1 = service.getVechicleDetails("src/main/resources/TestVehicle");

        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        driver.manage().window().maximize();
        GetVehicleInfoPage getVehicleInfoPage = new GetVehicleInfoPage(driver);
        getVehicleInfoPage.clickOnStartButton();

        VehicleEnquiryPage vehicleEnquiryPage = new VehicleEnquiryPage(driver);
        VehicleDetailsPage vehicleDetailsPage = new VehicleDetailsPage(driver);
        List<String[]> vehicleList = new ArrayList<>();
        String[] vechicleDetails = {"DA05BYP", "VAUXHALL", "BLUE", "VAUXALL"};
        vehicleList.add(vechicleDetails);
        for (String[] Info : vehicleList) {
            vehicleEnquiryPage.enterRegNumber(Info[0]);
            vehicleEnquiryPage.clickContinue();
            vehicleDetailsPage.validate(Info[1], Info[2]);
            vehicleDetailsPage.searchAgain();
        }
        vehicleList.size();
    }
}
