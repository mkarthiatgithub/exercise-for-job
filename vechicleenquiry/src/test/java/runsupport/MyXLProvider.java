package runsupport;

import com.google.inject.Provider;
import com.google.inject.name.Named;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.CSVReader;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.ExcelReader;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService;

public class MyXLProvider implements Provider<FileService> {

    @Override
    public FileService get() {
        return new ExcelReader();
    }
}
