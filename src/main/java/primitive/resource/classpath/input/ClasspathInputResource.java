package primitive.resource.classpath.input;

import com.google.inject.Inject;
import org.apache.commons.logging.Log;
import primitive.resource.InputResource;

import java.io.InputStream;

// todo doc
public class ClasspathInputResource implements InputResource {
    private Log log;

    private InputStream stream;
    private String path;

    @Inject
    public ClasspathInputResource(Log log) {
        this.log = log;
        log.debug(String.format("Initializing %s...", this.getClass().getName()));
    }

    @Override
    public InputStream getInputStream() {
        if (this.stream == null)
            this.stream = this.getClass().getResourceAsStream(this.getPath());

        return this.stream;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }
}
