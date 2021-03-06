package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceReader {

    private static final Logger logger = LoggerFactory.getLogger(ServiceReader.class);
    private FileService fileService;

    @Inject
    public ServiceReader(FileService fileService) {
        this.fileService = fileService;
    }

    public FileService getfileServiceBasedOnType(SupportedMIME supportedMIME, String dirName) {
        if (!fileService.getSupportedMIME().contains(supportedMIME)) {
            throw new RuntimeException("Non supprted MIME");
        }
        logger.info("Getting instance of supported MIME type ");
        if ("CSV".equalsIgnoreCase(supportedMIME.toString())) {
            fileService = new CSVReader();
            fileService.getVechicleDetails(dirName);
        } else if ("XLS".equalsIgnoreCase(supportedMIME.toString())) {
            fileService = new ExcelReader();
            fileService.getVechicleDetails(dirName);
        } else {
            throw new RuntimeException("Non supprted MIME");
        }
        return fileService;
    }
}
