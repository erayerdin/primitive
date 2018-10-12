package primitive;

import com.google.inject.AbstractModule;
import primitive.io.path.PathModule;
import primitive.log.LogModule;
import primitive.meta.MetaModule;
import primitive.system.SystemModule;

// todo doc
public class ApplicationModule extends AbstractModule {
    private volatile static ApplicationModule instance = null;

    protected ApplicationModule() {}

    @Override
    protected void configure() {
        super.configure();
        install(new LogModule());
        install(new SystemModule());
        install(new MetaModule());
        install(new PathModule());
    }

    public static ApplicationModule getInstance() {
        if (instance == null) {
            instance = new ApplicationModule();
        }

        return instance;
    }
}
