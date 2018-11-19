package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.List;

import static gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService.SupportedFiles.setListOfSupportedFiles;

public class ExcelReader implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(ServiceReader.class);
    private static XSSFSheet ExcelSheet;

    private static XSSFWorkbook ExcelWBook;


    @Override
    public List<FileInformation> getFileInfo(SupportedMIME supportedMIME, String dirName) {
        return setListOfSupportedFiles(dirName, supportedMIME);
    }

    @Override
    public List<vehicleDetail> getVechicleDetails(SupportedMIME supportedMIME, String dirName) {
        List<vehicleDetail> vehicleDetails = null;
        List<FileInformation> fileInformationList = getFileInfo(supportedMIME, dirName);
        for (FileInformation file : fileInformationList) {
            vehicleDetails = readAllRowsExcel(file.getFileName());
        }
        return vehicleDetails;
    }

    public List<vehicleDetail> readAllRowsExcel(String filePath) {
        String sheetName = "Sheet1";
        List<vehicleDetail> vehicleDetails = null;
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
            vehicleDetails.add(readExcelRow(filePath, sheetName, rowNum));

        }
        return vehicleDetails;
    }

    private vehicleDetail readExcelRow(String filePath, String sheetName, int rowNum) {
        vehicleDetail vehicle = null;
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
