package primitive;

import com.google.inject.AbstractModule;
import primitive.initializer.InitializerModule;

public class ApplicationModule extends AbstractModule {
    private volatile static ApplicationModule instance = null;

    private ApplicationModule() {}

    @Override
    protected void configure() {
        super.configure();
        install(new InitializerModule());
    }

    public static ApplicationModule getInstance() {
        if (instance == null) {
            instance = new ApplicationModule();
        }

        return instance;
    }
}
