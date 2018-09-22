package primitive.providers.resource;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @TestInJfxThread
    public void testClasspathResourceStream() throws IOException {
        InputStream stream = provider.getClasspathResourceStream("random.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);

        String content = buffer.lines().collect(Collectors.joining("\n"));

        assertThat(content.length() > 0, is(true));
        assertThat(content, is(equalTo("random")));

        buffer.close();
        reader.close();
        stream.close();
    }

    @Test
    @TestInJfxThread
    public void testAppDataResourceStream() throws IOException {
        // setup
        Path randomFile = Paths.get(
                provider.getLocalApplicationDataDirectory(false).toString(),
                "random.txt"
        );
        PrintWriter writer = new PrintWriter(randomFile.toFile());
        writer.print("random");
        writer.close();

        // test
        InputStream stream = provider.getApplicationDataResourceStream("random.txt", false, false);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);

        String content = buffer.lines().collect(Collectors.joining("\n"));

        assertThat(content.length() > 0, is(true));
        assertThat(content, is(equalTo("random")));

        // tear down
        buffer.close();
        reader.close();
        stream.close();
        Files.delete(randomFile);
    }
}