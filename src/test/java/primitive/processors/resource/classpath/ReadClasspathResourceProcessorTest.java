package primitive.processors.resource.classpath;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import primitive.ApplicationModule;
import primitive.processors.resource.ReadResourceProcessor;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class ReadClasspathResourceProcessorTest {
    private ReadResourceProcessor processor;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        this.processor = injector.getInstance(Key.get(ReadResourceProcessor.class, ReadClasspathResource.class));
        this.processor.setUp();
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testSize() {
        long size;

        size = this.processor.totalWorkProperty().get();
        assertThat(size, is(equalTo(0L)));

        this.processor.resourcePathProperty().setValue("random.txt");

        size = this.processor.totalWorkProperty().get();
        assertThat(size > 0L, is(true));
    }

    @Test
    @TestInJfxThread
    public void testReadBytesArray() throws IOException {
        this.processor.resourcePathProperty().setValue("random.txt");
        byte[] bytes = this.processor.readToBytes();

        assertThat(bytes.length, is(equalTo(6)));
    }

    @Test
    public void testReadWorkDone() throws IOException {
        this.processor.resourcePathProperty().setValue("random.txt");

        assertThat(this.processor.workDoneProperty().get(), is(equalTo(0L)));
        this.processor.readToBytes();
        assertThat(this.processor.workDoneProperty().greaterThan(0L).get(), is(true));
    }

    @Test
    public void testReadString() throws IOException {
        this.processor.resourcePathProperty().setValue("random.txt");
        String val = this.processor.readToString(Charset.defaultCharset());

        assertThat(val, is(equalTo("random")));
    }

    @After
    public void tearDown() throws Exception {
        this.processor.tearDown();
    }
}