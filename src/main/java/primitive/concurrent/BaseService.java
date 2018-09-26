package primitive.concurrent;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.concurrent.Service;
import org.apache.commons.logging.Log;
import primitive.providers.ProviderModule;
import primitive.providers.logging.LoggingProvider;

// todo doc
public abstract class BaseService <V> extends Service<V> {
    // todo doc
    protected volatile static Log log;

    public BaseService() {
        Injector injector = Guice.createInjector(new ProviderModule());
        LoggingProvider provider = injector.getInstance(LoggingProvider.class);

        log = provider.getLogger(this.getClass());
        log.info(String.format("Initializing Service<%s>...", this.getClass().getCanonicalName()));
    }

    @Override
    protected void ready() {
        super.ready();
        log.info(String.format("Service<%s> is ready.", this.getClass().getCanonicalName()));
    }

    @Override
    protected void scheduled() {
        super.scheduled();
        log.debug(String.format("Service<%s> has been scheduled.", this.getClass().getCanonicalName()));
    }

    @Override
    protected void running() {
        log.info(String.format("Service<%s> is running...", this.getClass().getCanonicalName()));
        super.running();
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        log.info(String.format("Service<%s has succeeded.", this.getClass().getCanonicalName()));
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        log.warn(String.format("Service<%s> has been cancelled.", this.getClass().getCanonicalName()));
    }

    @Override
    protected void failed() {
        super.failed();
        log.error(String.format("Service<%s> has failed due to %s. Message:\n%s",
                this.getClass().getCanonicalName(),
                this.getException().getClass().getCanonicalName(),
                this.getException().getMessage()));
    }

    @Override
    public boolean cancel() {
        log.warn(String.format("Service<%s> is being cancelled.",
                this.getClass().getCanonicalName()));
        return super.cancel();
    }

    @Override
    public void restart() {
        super.restart();
        log.info(String.format("Service<%s> has been restarted.", this.getClass().getCanonicalName()));
    }

    @Override
    public void reset() {
        super.reset();
        log.info(String.format("Service<%s> has been reset.", this.getClass().getCanonicalName()));
    }

    @Override
    public void start() {
        super.start();
        log.info(String.format("Service<%s> has started.", this.getClass().getCanonicalName()));
    }
}