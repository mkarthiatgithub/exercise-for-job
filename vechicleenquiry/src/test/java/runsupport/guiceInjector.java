package runsupport;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

public class GuiceInjector {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(Stage.PRODUCTION, new MyModule());
    }
}
