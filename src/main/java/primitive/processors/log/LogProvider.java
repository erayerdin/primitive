package primitive.processors.log;

import com.google.inject.Provider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// todo doc
public class LogProvider implements Provider<Log> {
    @Override
    public Log get() {
        return LogFactory.getLog("app");
    }
}
