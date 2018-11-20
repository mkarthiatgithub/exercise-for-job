package runsupport;

import com.google.inject.name.Names;
import gov.uk.digital.dvla.vechicleenquiry.servicelayer.FileService;

public class MyModule extends com.google.inject.AbstractModule {
    @Override
    protected void configure() {
        bind(FileService.class).annotatedWith(Names.named("CSV")).toProvider(MyCSVProvider.class);
        bind(FileService.class).annotatedWith(Names.named("XLS")).toProvider(MyXLProvider.class);
    }
}
