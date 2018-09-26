package primitive.providers;

import com.google.inject.AbstractModule;
import primitive.providers.logging.GenericLoggingProvider;
import primitive.providers.logging.LoggingProvider;

public class ProviderModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        bind(LoggingProvider.class).to(GenericLoggingProvider.class);
    }
}
