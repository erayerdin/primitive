package primitive.processors.system;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import primitive.ApplicationModule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class OperatingSystemProcessorTest {
    private static OperatingSystemProcessor processor;

    @BeforeClass
    public static void setUp() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        processor = injector.getInstance(OperatingSystemProcessor.class);
        processor.setUp();
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testTypeValue() {
        assertThat(processor.getType(), is(notNullValue()));

        String name = System.getProperty("os.name").toLowerCase();

        if (name.contains("linux"))
            assertThat(processor.getType(), is(equalTo(OperatingSystemType.LINUX)));
    }

    @Test
    @TestInJfxThread
    public void testVersionValue() {
        assertThat(processor.getVersion(), is(notNullValue()));

        String name = System.getProperty("os.name").toLowerCase();

        if (name.contains("linux"))
            assertThat(processor.getVersion().contains("."), is(true));
    }
}