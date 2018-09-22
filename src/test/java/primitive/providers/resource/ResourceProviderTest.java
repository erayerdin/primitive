package primitive.providers.resource;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class ResourceProviderTest {
    private static ResourceProvider provider;

    @BeforeClass
    public static void setUpClass() throws Exception {
        provider = ResourceProvider.getInstance();
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testLocalAppDataExists() {
        Path path = provider.getLocalApplicationDataDirectory(false);
        assertThat(Files.exists(path), is(true));
    }

    @Test
    @TestInJfxThread
    public void testLocalVersionAppDataExists() {
        Path path = provider.getLocalApplicationDataDirectory(true);
        assertThat(Files.exists(path), is(true));
        // because version does not exist in primitive.meta.properties
        // it returns the same value as non-version-based app-data
    }

    @Test
    @TestInJfxThread
    public void testGlobalAppDataExists() {
        Path path = provider.getGlobalApplicationDataDirectory(false);
        assertThat(Files.exists(path), is(true));
    }

    @Test
    @TestInJfxThread
    public void testGlobalVersionAppDataExists() {
        Path path = provider.getGlobalApplicationDataDirectory(true);
        assertThat(Files.exists(path), is(true));
        // because version does not exist in primitive.meta.properties
        // it returns the same value as non-version-based app-data
    }

    @Test
    public void testClassPathResourceStream() {
        InputStream stream = provider.getClasspathResourceStream("random.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);

        String content = buffer.lines().collect(Collectors.joining("\n"));

        assertThat(content.length() > 0, is(true));
        assertThat(content, is(equalTo("random")));
    }
}