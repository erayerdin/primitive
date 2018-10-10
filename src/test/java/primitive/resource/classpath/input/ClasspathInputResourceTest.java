package primitive.resource.classpath.input;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import org.junit.Before;
import org.junit.Test;
import primitive.ApplicationModule;
import primitive.resource.InputResource;
import primitive.resource.classpath.Classpath;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ClasspathInputResourceTest {
    private InputResource resource;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        this.resource = injector.getInstance(Key.get(InputResource.class, Classpath.class));
    }

    @Test
    public void testInputStreamSame() {
        this.resource.setPath("random.txt");

        InputStream stream1 = this.resource.getInputStream();
        InputStream stream2 = this.resource.getInputStream();

        assertThat(stream1, is(stream2));
    }

    @Test
    public void testRead() throws IOException {
        this.resource.setPath("/random.txt");
        InputStream stream = this.resource.getInputStream();

        StringBuilder builder = new StringBuilder();

        int i;
        while ((i = stream.read()) != -1) {
            builder.append((char) i);
        }

        assertThat(builder.toString(), is(equalTo("random")));
    }
}