package primitive.providers.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// todo doc
public class GenericLoggingProvider implements LoggingProvider {
    @Override
    public void start() throws Exception {}

    @Override
    public void stop() throws Exception {}

    @Override
    public Log getLogger(Class clazz) {
        return LogFactory.getLog(clazz);
    }

    @Override
    public Log getLogger(String name) {
        return LogFactory.getLog(name);
    }
}
