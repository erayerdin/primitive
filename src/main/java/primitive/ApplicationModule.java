package primitive;

import com.google.inject.AbstractModule;
import primitive.resource.ResourceModule;
import primitive.providers.ProviderModule;

// todo doc
public class ApplicationModule extends AbstractModule {
    private volatile static ApplicationModule instance = null;

    protected ApplicationModule() {}

    @Override
    protected void configure() {
        super.configure();
        install(new ProviderModule());
        install(new ResourceModule());
    }

    public static ApplicationModule getInstance() {
        if (instance == null) {
            instance = new ApplicationModule();
        }

        return instance;
    }
}
