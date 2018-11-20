package steps;

import com.google.inject.Inject;
import com.google.inject.name.Named;
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
    private FileService csvService;
    private FileService excelService;

    @Inject
    public DVLAVechicleSteps(CSVReader csvReader,
                             ExcelReader excelReader) {
        this.csvService = csvReader;
        this.excelService = excelReader;
    }

    private String PAGE_URL = "https://www.gov.uk/get-vehicle-information-from-dvla";

    protected static Logger log = Logger.getLogger(DVLAVechicleSteps.class);

    @Given("^Am on DVLA Site$")
    public void amOnDVLASite() {
         List<VehicleDetail> vechicleDetails1 = csvService.getVechicleDetails("src/main/resources/TestVehicle");
        List<VehicleDetail> vechicleDetails12 = excelService.getVechicleDetails("src/main/resources/TestVehicle");

        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        driver.manage().window().maximize();
        GetVehicleInfoPage getVehicleInfoPage = new GetVehicleInfoPage(driver);
        getVehicleInfoPage.clickOnStartButton();

        VehicleEnquiryPage vehicleEnquiryPage = new VehicleEnquiryPage(driver);
        VehicleDetailsPage vehicleDetailsPage = new VehicleDetailsPage(driver);

//        for (VehicleDetail singleCar : vechicleDetails1) {
//            vehicleEnquiryPage.enterRegNumber(singleCar.getRegNumber());
//            vehicleEnquiryPage.clickContinue();
//            vehicleDetailsPage.validate(singleCar.getMake(), singleCar.getColor());
//            vehicleDetailsPage.searchAgain();
//        }

        for (VehicleDetail singleCar : vechicleDetails12) {
            vehicleEnquiryPage.enterRegNumber(singleCar.getRegNumber());
            vehicleEnquiryPage.clickContinue();
            vehicleDetailsPage.validate(singleCar.getMake(), singleCar.getColor());
            vehicleDetailsPage.searchAgain();
        }

    }
}
