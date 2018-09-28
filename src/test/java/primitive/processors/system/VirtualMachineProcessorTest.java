package primitive.processors.system;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import primitive.ApplicationModule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class VirtualMachineProcessorTest {
    private static VirtualMachineProcessor processor;

    @BeforeClass
    public static void setUp() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        processor = injector.getInstance(VirtualMachineProcessor.class);
        processor.setUp();
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testArch() {
        assertThat(processor.getArch(), is(notNullValue()));
        assertThat(processor.getArch().length() > 0, is(true));
    }
}