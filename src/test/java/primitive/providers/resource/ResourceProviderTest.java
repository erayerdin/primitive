package primitive.providers.resource;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.Files;
import java.nio.file.Path;

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
        System.out.println(path.toString());
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
}