package runsupport;

import gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService;

public class myModule extends com.google.inject.AbstractModule {
    @Override
    protected void configure() {
        bind(FileService.class).toProvider(myProvider.class);
    }
}
