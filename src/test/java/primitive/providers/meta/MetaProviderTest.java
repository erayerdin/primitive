package primitive.providers.meta;

import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(JfxRunner.class)
public class MetaProviderTest {
    private volatile static MetaProvider provider;

    @BeforeClass
    public static void setUpClass() throws Exception {
        provider = MetaProvider.getInstance();
        Thread.sleep(500);
    }

    @Test
    @TestInJfxThread
    public void testCrName() {
        assertThat(provider.getCrName(), is(equalTo("example_project")));
    }

    @Test
    @TestInJfxThread
    public void testHrName() {
        assertThat(provider.getHrName(), is(equalTo("Example Project")));
    }

    @Test
    @TestInJfxThread
    public void testVersion() {
        assertThat(provider.getVersion(), is(nullValue()));
    }
}