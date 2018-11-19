package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader implements FileService {

    @Override
    public List<vehicleDetail> getVechicleDetails(SupportedMIME supportedMIME,String dirName) {

        List<vehicleDetail> vehicleDetails = null;
        List<FileInformation> fileInformations = getFileInfo(supportedMIME, dirName);
        for (FileInformation fileInformation : fileInformations) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileInformation.getFileName()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String cvsSplitBy = ",";
                    String[] details = line.split(cvsSplitBy);
                    vehicleDetail vehicle = new vehicleDetail(details[0], details[1], details[2]);
                    vehicleDetails.add(vehicle);
                }
            } catch (IOException e) {
                throw new RuntimeException("something Wrong " + e.getMessage());
            }
        }
        return vehicleDetails;
    }


    @Override
    public List<FileInformation> getFileInfo(SupportedMIME supportedMIME, String dirName) {
        return SupportedFiles.setListOfSupportedFiles(dirName, supportedMIME);
    }
}
