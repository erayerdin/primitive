package primitive.io.path.appdata;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import primitive.ApplicationModule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApplicationDataProviderLinuxTest {
    private Path path;

    @Before
    public void setUp() throws Exception {
        String name = System.getProperty("os.name").toLowerCase();
        Assume.assumeThat(
                "This test can only be run on a Linux distribution.",
                name.contains("linux"),
                is(true)
        );

        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        this.path = injector.getInstance(Key.get(Path.class, ApplicationDataPath.class));
    }

    @Test
    public void pathExists() {
        assertThat(Files.exists(this.path), is(true));
        assertThat(Files.isDirectory(this.path), is(true));
    }

    @Test
    public void nameContainsApplicationName() {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());
        Properties meta = injector.getInstance(Properties.class);

        String appName = meta.getProperty("name");
        String fullPath = this.path.toString();

        assertThat(fullPath.contains(appName), is(true));
    }
}