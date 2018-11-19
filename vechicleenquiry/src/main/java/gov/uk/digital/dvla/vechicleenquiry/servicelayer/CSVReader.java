package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader implements FileService {

    SupportedMIME supportedMIME = SupportedMIME.CSV;

    @Override
    public List<VehicleDetail> getVechicleDetails(String dirName) {

        List<VehicleDetail> VehicleDetails = null;
        List<FileInformation> fileInformations = getFileInfo(dirName);
        for (FileInformation fileInformation : fileInformations) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileInformation.getFileName()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String cvsSplitBy = ",";
                    String[] details = line.split(cvsSplitBy);
                    VehicleDetail vehicle = new VehicleDetail(details[0], details[1], details[2]);
                    VehicleDetails.add(vehicle);
                }
            } catch (IOException e) {
                throw new RuntimeException("something Wrong " + e.getMessage());
            }
        }
        return VehicleDetails;
    }


    @Override
    public List<FileInformation> getFileInfo(String dirName) {
        return SupportedFiles.setListOfSupportedFiles(dirName, supportedMIME);
    }
}
