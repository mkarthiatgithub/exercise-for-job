package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import cucumber.runtime.junit.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CSVReaderTest {
    private CSVReader csvReader;

    @Before
    public void setUp() throws Exception {
        csvReader = new CSVReader();
    }

    @Test
    public void getVechicleDetails() {
        List<VehicleDetail> vechicleDetails = csvReader.getVechicleDetails("src/main/resources/testData");
        Assert.assertEquals(2,vechicleDetails.size());
        Assert.assertEquals(vechicleDetails.get(0).getColor(),"BLUE");
    }

    @Test
    public void getFileInfo() {
        List<FileInformation> fileInfo = csvReader.getFileInfo("src/main/resources/testData");
        Assert.assertEquals("csv",fileInfo.get(0).getExt());
    }
}