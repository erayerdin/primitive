package primitive.providers.logging;

import org.apache.commons.logging.Log;
import primitive.providers.BaseProvider;

// todo doc
public interface LoggingProvider extends BaseProvider {
    // todo doc
    Log getLogger(Class clazz);
    Log getLogger(String name);
}
