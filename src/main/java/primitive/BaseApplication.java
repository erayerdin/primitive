package primitive;

import com.google.inject.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import primitive.lifecycles.ApplicationService;
import primitive.lifecycles.Destroyable;
import primitive.lifecycles.Initializable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class BaseApplication extends Application {
    private volatile static Injector injector = Guice.createInjector(ApplicationModule.getInstance());
    private Log log;

    public BaseApplication() {
        log = injector.getInstance(Log.class);
    }

    @Override
    public void init() throws Exception {
        super.init();
        log.info("Initializing application...");

        this.initializeApplicationServices();
    }

    private void initializeApplicationServices() {
        log.debug("Initializing application services...");

        log.debug("Filtering singleton initializable application services...");
        Map<Key<?>, Binding<?>> allBindings = injector.getAllBindings();
        List<?> initializables = allBindings.entrySet().stream()
                .filter(k -> k.getValue().getProvider().get().getClass().isAnnotationPresent(Singleton.class))
                .filter(k -> k.getValue().getProvider().get().getClass().isAnnotationPresent(ApplicationService.class))
                .filter(k -> k.getValue().getProvider().get() instanceof Initializable)
                .map(k -> k.getValue().getProvider().get())
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .collect(Collectors.toList());

        log.debug(String.format("Initializing %d application services...", initializables.size()));
        initializables.forEach(o -> {
            Initializable i = (Initializable) o;
            try {
                i.initialize();
            } catch (Exception e) {
                log.error(String.format("An error occured while initializing %s.", i), e);
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        log.info("Stopping application...");
        
        this.destroyApplicationServices();
    }

    private void destroyApplicationServices() {
        log.debug("Destroying application services...");

        log.debug("Filtering singleton destroyable application services...");
        Map<Key<?>, Binding<?>> allBindings = injector.getAllBindings();
        List<?> destroyables = allBindings.entrySet().stream()
                .filter(k -> k.getValue().getProvider().get().getClass().isAnnotationPresent(Singleton.class))
                .filter(k -> k.getValue().getProvider().get().getClass().isAnnotationPresent(ApplicationService.class))
                .filter(k -> k.getValue().getProvider().get() instanceof Destroyable)
                .map(k -> k.getValue().getProvider().get())
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .collect(Collectors.toList());

        log.debug(String.format("Destroying %d application services...", destroyables.size()));
        destroyables.forEach(o -> {
            Destroyable i = (Destroyable) o;
            try {
                i.destroy();
            } catch (Exception e) {
                log.error(String.format("An error occured while destroying %s.", i), e);
            }
        });
    }

    @Override
    public abstract void start(Stage primaryStage) throws Exception;

    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        BaseApplication.injector = injector;
    }
}
