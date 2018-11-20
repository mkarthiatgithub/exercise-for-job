package runsupport;

import com.google.inject.Provider;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.ExcelReader;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService;

public class MyXLProvider implements Provider<FileService> {

    @Override
    public FileService get() {
        return new ExcelReader();
    }
}
