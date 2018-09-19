package primitive.providers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// todo doc
public abstract class BaseProvider {
    // todo doc
    protected volatile static Log log;

    protected BaseProvider() {
        log = LogFactory.getLog(this.getClass());
        log.info(String.format("Initializing Provider<%s>...", this.getClass().getCanonicalName()));
        this.init();
    }

    protected abstract void init();
    protected abstract void uninit();
}
