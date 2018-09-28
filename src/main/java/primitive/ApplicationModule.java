package primitive;

import com.google.inject.AbstractModule;
import primitive.processors.ProcessorModule;

public class ApplicationModule extends AbstractModule {
    private volatile static ApplicationModule instance = null;

    private ApplicationModule() {}

    @Override
    protected void configure() {
        super.configure();
        install(new ProcessorModule());
    }

    public static ApplicationModule getInstance() {
        if (instance == null) {
            instance = new ApplicationModule();
        }

        return instance;
    }
}
