package primitive.processors.resource.classpath;

import com.google.inject.Inject;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import org.apache.commons.logging.Log;
import primitive.processors.resource.ReadResourceProcessor;
import primitive.processors.resource.listeners.ResourcePathSizeListener;
import primitive.processors.system.OperatingSystemProcessor;

import java.nio.file.Path;

// todo doc
public class ReadClasspathResourceProcessor implements ReadResourceProcessor {
    private Log log;
    private OperatingSystemProcessor operatingSystemProcessor;

    private LongProperty workDone;
    private LongProperty totalWork;

    private StringProperty resourcePath;
    private ChangeListener<String> resourcePathSizeListener;

    private byte[] content;

    @Inject
    public ReadClasspathResourceProcessor(Log log, OperatingSystemProcessor operatingSystemProcessor) {
        this.log = log;
        log.info(String.format("Initializing %s...", this.getClass().getName()));

        this.operatingSystemProcessor = operatingSystemProcessor;

        this.workDone = new SimpleLongProperty();
        this.totalWork = new SimpleLongProperty();
        this.resourcePath = new SimpleStringProperty();
    }

    @Override
    public void setUp() {
        log.info(String.format("Setting up %s...", this.getClass().getName()));

        log.info("Adding listeners...");
        log.debug("Adding ResourcePathSizeListener...");
        this.resourcePathSizeListener = new ResourcePathSizeListener(this);
        this.resourcePathProperty().addListener(this.resourcePathSizeListener);
    }

    @Override
    public void tearDown() {
        log.info(String.format("Tearing down %s...", this.getClass().getName()));

        log.info("Removing listeners...");
        log.debug("Removing ResourcePathSizeListener...");
        if (this.resourcePathSizeListener != null) {
            this.resourcePathProperty().removeListener(this.resourcePathSizeListener);
        }
    }

    @Override
    public byte[] readToBytes() {
        if (this.content != null)
            return this.content;

        return new byte[0];
    }

    @Override
    public String readToString() {
        return null;
    }

    @Override
    public Path getBasePath() {
        return null;
    }

    public long getWorkDone() {
        return workDone.get();
    }

    @Override
    public LongProperty workDoneProperty() {
        return workDone;
    }

    public void setWorkDone(long workDone) {
        this.workDone.set(workDone);
    }

    public long getTotalWork() {
        return totalWork.get();
    }

    @Override
    public LongProperty totalWorkProperty() {
        return totalWork;
    }

    public void setTotalWork(long totalWork) {
        this.totalWork.set(totalWork);
    }

    public String getResourcePath() {
        return resourcePath.get();
    }

    @Override
    public StringProperty resourcePathProperty() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath.set(resourcePath);
    }
}
