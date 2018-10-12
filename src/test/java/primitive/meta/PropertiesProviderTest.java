package primitive.meta;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import primitive.ApplicationModule;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PropertiesProviderTest {
    private Properties meta;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        this.meta = injector.getInstance(Properties.class);
    }

    @Test
    public void testSingleton() {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        Properties meta2 = injector.getInstance(Properties.class);
        assertThat(meta2, is(this.meta));
    }

    @Test
    public void testName() {
        assertThat(this.meta.getProperty("name"), is(equalTo("Sample Project")));
    }

    @Test
    public void testVersion() {
        assertThat(this.meta.getProperty("version"), is(nullValue()));
    }
}