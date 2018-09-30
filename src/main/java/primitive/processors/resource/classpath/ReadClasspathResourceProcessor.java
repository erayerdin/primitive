package primitive.processors.resource.classpath;

import com.google.inject.Inject;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import org.apache.commons.logging.Log;
import primitive.processors.resource.ReadResourceProcessor;
import primitive.processors.resource.listeners.ResourceSizeListener;
import primitive.processors.system.OperatingSystemProcessor;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;

// todo doc
public class ReadClasspathResourceProcessor implements ReadResourceProcessor {
    private Log log;

    private LongProperty workDone;
    private LongProperty totalWork;

    private StringProperty resourcePath;
    private ChangeListener<String> resourcePathSizeListener;

    private int bufferSize;

    private byte[] content;

    @Inject
    public ReadClasspathResourceProcessor(Log log) {
        this.log = log;
        log.info(String.format("Initializing %s...", this.getClass().getName()));

        this.workDone = new SimpleLongProperty(0);
        this.totalWork = new SimpleLongProperty(0);
        this.resourcePath = new SimpleStringProperty();

        this.bufferSize = 8 * 1024;
    }

    @Override
    public void setUp() {
        log.info(String.format("Setting up %s...", this.getClass().getName()));

        log.info("Adding listeners...");
        log.debug("Adding ResourcePathSizeListener...");
        this.resourcePathSizeListener = new ResourceSizeListener(this);
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
    public byte[] readToBytes() throws IOException {
        if (this.content != null)
            return this.content;

        InputStream stream = ClassLoader.getSystemResourceAsStream(this.getResourcePath());
        BufferedInputStream buffer = new BufferedInputStream(stream, this.getBufferSize());

        byte[] bytes = new byte[(int) this.getTotalWork()];

        int b;
        long counter = 0;
        this.setWorkDone(0L);
        while ((b = buffer.read()) != -1) {
            bytes[(int) counter] = (byte) b; // todo might lead to integer overflow since we cast long to int
            counter++;
            this.setWorkDone(counter);
        }

        buffer.close();
        stream.close();

        this.content = bytes;
        return this.content;
    }

    @Override
    public String readToString(Charset charset) throws IOException {
        this.readToBytes();

        return new String(this.content, charset);
    }

    @Override
    public int getBufferSize() {
        return this.bufferSize;
    }

    @Override
    public void setBufferSize(int size) {
        this.bufferSize = size;
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
