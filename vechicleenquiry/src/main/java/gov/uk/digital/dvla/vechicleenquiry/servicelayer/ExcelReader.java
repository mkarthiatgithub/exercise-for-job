package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.List;

import static gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService.SupportedFiles.setListOfSupportedFiles;

public class ExcelReader implements FileService {

    SupportedMIME supportedMIME = SupportedMIME.XLS;
    private static final Logger logger = LoggerFactory.getLogger(ServiceReader.class);
    private static XSSFSheet ExcelSheet;

    private static XSSFWorkbook ExcelWBook;


    @Override
    public List<FileInformation> getFileInfo(String dirName) {
        return setListOfSupportedFiles(dirName, supportedMIME);
    }

    @Override
    public List<VehicleDetail> getVechicleDetails(String dirName) {
        List<VehicleDetail> VehicleDetails = null;
        List<FileInformation> fileInformationList = getFileInfo(dirName);
        for (FileInformation file : fileInformationList) {
            VehicleDetails = readAllRowsExcel(file.getFileName());
        }
        return VehicleDetails;
    }

    public List<VehicleDetail> readAllRowsExcel(String filePath) {
        String sheetName = "Sheet1";
        List<VehicleDetail> VehicleDetails = null;
        int rows = 0;
        try {
            FileInputStream ExcelFile = new FileInputStream(filePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWBook.getSheet(sheetName);
            rows = ExcelSheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }

        for (int rowNum = 0; rowNum < rows; rowNum++) {
            VehicleDetails.add(readExcelRow(filePath, sheetName, rowNum));

        }
        return VehicleDetails;
    }

    private VehicleDetail readExcelRow(String filePath, String sheetName, int rowNum) {
        VehicleDetail vehicle = null;
        String[] vehicleData = new String[3];
        try {
            FileInputStream ExcelFile = new FileInputStream(filePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWBook.getSheet(sheetName);
            vehicleData[0] = (ExcelSheet.getRow(rowNum).getCell(0).getStringCellValue());
            vehicleData[1] = (ExcelSheet.getRow(rowNum).getCell(1).getStringCellValue());
            vehicleData[2] = (ExcelSheet.getRow(rowNum).getCell(2).getStringCellValue());

            vehicle.setRegNumber(vehicleData[0]);
            vehicle.setMake(vehicleData[1]);
            vehicle.setColor(vehicleData[2]);
            return vehicle;

        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new RuntimeException("Something Wrong " + e.getMessage());
        }
    }


}
