package primitive.providers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.logging.Log;
import org.junit.BeforeClass;
import org.junit.Test;
import primitive.ApplicationModule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class LogProviderTest {
    private static Log log;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        log = injector.getInstance(Log.class);
    }

    @Test
    public void testInstance() {
        assertThat(log, is(notNullValue()));
        log.debug("An example debug log");
    }

    @Test
    public void testSingleton() {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        Log log2 = injector.getInstance(Log.class);

        assertThat(log2, is(log));
    }
}