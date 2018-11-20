package runsupport;

import com.google.inject.Provider;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.CSVReader;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService;

public class MyCSVProvider implements Provider<FileService> {

    @Override
    public FileService get() {
        return new CSVReader();
    }
}
