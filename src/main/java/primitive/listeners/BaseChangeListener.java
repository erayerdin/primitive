package primitive.listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.beans.value.ChangeListener;
import org.apache.commons.logging.Log;
import primitive.ApplicationModule;

// todo doc
public abstract class BaseChangeListener <T> implements ChangeListener<T> {
    protected volatile static Log log;

    public BaseChangeListener() {
        Injector injector = Guice.createInjector(ApplicationModule.getInstance());

        log = injector.getInstance(Log.class);
        log.info(String.format("Initializing ChangeListener<%s>...", this.getClass().getCanonicalName()));
    }
}
