package primitive.providers;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.apache.commons.logging.Log;
import primitive.providers.log.LogProvider;

public class ProviderModule extends AbstractModule {
    private volatile Log log;

    @Override
    public void configure() {
        bind(Log.class).toProvider(LogProvider.class);
    }
}
