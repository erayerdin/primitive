package primitive.application;

import javafx.application.Application;
import primitive.providers.BaseProvider;

import java.util.Set;

// todo doc
public abstract class BaseApplication <P extends BaseProvider> extends Application {
    private Set<P> providers;

    public P getProvider(Class<P> provider) {
        return null;
    }
}
