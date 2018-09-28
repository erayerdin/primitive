package primitive.concurrent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.concurrent.Service;
import org.apache.commons.logging.Log;
import primitive.ApplicationModule;

// todo doc
public abstract class BaseService <V> extends Service<V> {
    // todo doc
    protected volatile static Log log;

    public BaseService() {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());

        log = injector.getInstance(Log.class);
        log.info(String.format("Initializing Service: %s...", this.getClass().getCanonicalName()));
    }

    @Override
    protected void ready() {
        super.ready();
        log.info("Service is ready.");
    }

    @Override
    protected void scheduled() {
        super.scheduled();
        log.debug("Service has been scheduled.");
    }

    @Override
    protected void running() {
        log.info("Service is running...");
        super.running();
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        log.info("Service has succeeded.");
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        log.warn("Service has been cancelled.");
    }

    @Override
    protected void failed() {
        super.failed();
        log.error(String.format("Service has failed due to %s. Message:\n%s",
                this.getClass().getCanonicalName(),
                this.getException().getClass().getCanonicalName(),
                this.getException().getMessage())
        );
    }

    @Override
    public boolean cancel() {
        log.warn("Service is being cancelled.");
        return super.cancel();
    }

    @Override
    public void restart() {
        super.restart();
        log.info("Service has been restarted.");
    }

    @Override
    public void reset() {
        super.reset();
        log.info("Service has been reset.");
    }

    @Override
    public void start() {
        super.start();
        log.info("Service has started.");
    }
}