package primitive.system.os;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import primitive.ApplicationModule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OperatingSystemProviderTest {
    private OperatingSystem operatingSystem;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        this.operatingSystem = injector.getInstance(OperatingSystem.class);
    }

    @Test
    public void testSingleton() {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        OperatingSystem operatingSystem2 = injector.getInstance(OperatingSystem.class);

        assertThat(operatingSystem2, is(this.operatingSystem));
    }
}