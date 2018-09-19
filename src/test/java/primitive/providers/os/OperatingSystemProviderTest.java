package primitive.providers.os;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class OperatingSystemProviderTest {
    private static OperatingSystemProvider provider;

    @BeforeClass
    public static void setUp() throws Exception {
        provider = OperatingSystemProvider.getInstance();
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testName() {
        String name = provider.getName();
        assertThat(name.length() > 0, is(true));
    }

    @Test
    @TestInJfxThread
    public void testVersion() {
        String version = provider.getVersion();
        assertThat(version.length() > 0, is(true));
    }

    @Test
    @TestInJfxThread
    public void testArch() {
        String arch = provider.getArch();
        assertThat(arch.length() > 0, is(true));
    }
}