package runsupport;

import com.google.inject.Provider;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.CSVReader;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.SupportedMIME;

public class myProvider implements Provider<FileService> {

    private SupportedMIME supportedMIME;
    private String dirName;

    @Override
    public FileService get() {
        return new CSVReader();
    }
}
