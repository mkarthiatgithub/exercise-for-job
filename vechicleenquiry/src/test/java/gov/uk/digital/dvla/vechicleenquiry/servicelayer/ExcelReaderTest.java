package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ExcelReaderTest {
    private ExcelReader excelReader;

    @Before
    public void setUp() {
        excelReader = new ExcelReader();
    }

    @Test
    public void getFileInfo() {
        List<FileInformation> dummy = excelReader.getFileInfo("dummy");
        Assert.assertEquals(dummy.size(), 0);
    }

    @Test
    public void getVechicleDetails() {
        List<VehicleDetail> vechicleDetails = excelReader.getVechicleDetails("src/main/resources/testData");
        Assert.assertNotNull(vechicleDetails);
    }

}