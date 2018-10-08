package primitive.lifecycles;

import com.google.inject.Guice;
import com.google.inject.Singleton;
import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testfx.api.FxToolkit;
import primitive.ApplicationModule;
import primitive.BaseApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

interface AnyService extends Initializable {}

@Singleton
@ApplicationService
class AnyServiceImpl implements AnyService {
    public volatile boolean isStarted = false;

    @Override
    public void initialize() throws Exception {
        this.isStarted = true;
    }
}

class AnyModule extends ApplicationModule {
    private volatile static AnyModule instance = null;

    @Override
    protected void configure() {
        super.configure();
        bind(AnyService.class).to(AnyServiceImpl.class);
    }

    public static AnyModule getInstance() {
        if (instance == null)
            instance = new AnyModule();

        return instance;
    }
}

public class ApplicationServiceTest {
    @Before
    public void setUp() throws Exception {
        AnyApplication application = new AnyApplication();
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> application);
        FxToolkit.cleanupApplication(application);
//        Thread.sleep(500);
    }

    @Test
    public void testApplicationService() {
        AnyServiceImpl service = (AnyServiceImpl) AnyApplication.getInjector().getInstance(AnyService.class);
        assertThat(service.isStarted, is(true));
    }
}
