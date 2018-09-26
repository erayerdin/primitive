package primitive.providers.logging;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.logging.Log;
import org.junit.BeforeClass;
import org.junit.Test;
import primitive.providers.ProviderModule;

// this must be tested by hand and output must be observed
public class GenericLoggingProviderTest {
    private static Injector injector;

    @BeforeClass
    public static void setUpClass() {
        injector = Guice.createInjector(new ProviderModule());
    }

    @Test
    public void testLogger() {
        LoggingProvider provider = injector.getInstance(LoggingProvider.class);
        Log logger = provider.getLogger("Sample Logger");
        logger.debug("This is a sample log.");
    }
}
