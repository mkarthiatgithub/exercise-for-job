package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService.SupportedFiles.getListOfSupportedFiles;

/**
 * This class read XLSX file with Sheet1 as sheet name and produces vechicle details
 */
public class ExcelReader implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelReader.class);
    private static XSSFSheet ExcelSheet;
    private static XSSFWorkbook ExcelWBook;
    private final SupportedMIME supportedMIME = SupportedMIME.XLSX;

    @Override
    public List<FileInformation> getFileInfo(String dirName) {
        return getListOfSupportedFiles(dirName, supportedMIME);
    }

    @Override
    public List<VehicleDetail> getVechicleDetails(String dirName) {
        List<VehicleDetail> VehicleDetails = null;
        List<FileInformation> fileInformationList = getFileInfo(dirName);
        for (FileInformation file : fileInformationList) {
            VehicleDetails = readAllRowsExcel(dirName + "\\" + file.getFileName());
        }
        return VehicleDetails;
    }

    public List<VehicleDetail> readAllRowsExcel(String filePath) {
        String sheetName = "Sheet1";
        List<VehicleDetail> VehicleDetails = new ArrayList<>();
        int rows = 0;
        try {
            FileInputStream ExcelFile = new FileInputStream(filePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWBook.getSheet(sheetName);
            rows = ExcelSheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new RuntimeException("something Wrong " + e.getMessage());
        }

        for (int rowNum = 0; rowNum < rows; rowNum++) {
            VehicleDetails.add(readExcelRow(filePath, sheetName, rowNum));

        }
        return VehicleDetails;
    }

    private VehicleDetail readExcelRow(String filePath, String sheetName, int rowNum) {
        String[] vehicleData = new String[3];
        try {
            FileInputStream ExcelFile = new FileInputStream(filePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWBook.getSheet(sheetName);
            vehicleData[0] = (ExcelSheet.getRow(rowNum).getCell(0).getStringCellValue());
            vehicleData[1] = (ExcelSheet.getRow(rowNum).getCell(1).getStringCellValue());
            vehicleData[2] = (ExcelSheet.getRow(rowNum).getCell(2).getStringCellValue());
            return new VehicleDetail(vehicleData[0], vehicleData[1], vehicleData[2]);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Something Wrong " + e.getMessage());
        }
    }


}
