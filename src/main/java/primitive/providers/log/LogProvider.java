package primitive.providers.log;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// todo class
@Singleton
public class LogProvider implements Provider<Log> {
    @Inject
    public LogProvider() {}

    @Override
    public Log get() {
        return LogFactory.getLog("app");
    }
}
