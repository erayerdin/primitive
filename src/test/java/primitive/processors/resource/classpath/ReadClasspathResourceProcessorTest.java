package primitive.processors.resource.classpath;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import primitive.ApplicationModule;
import primitive.processors.resource.ReadResourceProcessor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class ReadClasspathResourceProcessorTest {
    private static ReadResourceProcessor processor;

    @BeforeClass
    public static void setUp() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        processor = injector.getInstance(Key.get(ReadResourceProcessor.class, ReadClasspathResource.class));
        processor.setUp();
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testSize() {
        long size;

        size = processor.totalWorkProperty().get();
        assertThat(size, is(equalTo(0L)));

        processor.resourcePathProperty().setValue("random.txt");

        size = processor.totalWorkProperty().get();
        assertThat(size > 0L, is(true));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        processor.tearDown();
    }
}