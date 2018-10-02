package primitive.processors.meta;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import primitive.ApplicationModule;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class MetaProcessorTest {
    private volatile static MetaProcessor processor;

    @BeforeClass
    public static void setUpClass() throws IOException, InterruptedException {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        processor = injector.getInstance(MetaProcessor.class);
        try {
            processor.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testName() {
        assertThat(processor.nameProperty().getValue(), is(equalTo("Sample Project")));
    }

    @Test
    @TestInJfxThread
    public void testVersion() {
        assertThat(processor.versionProperty().getValue(), is(equalTo("")));
    }
}