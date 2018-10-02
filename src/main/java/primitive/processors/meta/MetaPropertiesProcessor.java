package primitive.processors.meta;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.logging.Log;
import primitive.processors.resource.ReadResourceProcessor;
import primitive.processors.resource.classpath.ReadClasspathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Properties;

// todo doc
@Singleton
public class MetaPropertiesProcessor implements MetaProcessor {
    private volatile static String propertiesPath = "primitive.meta.properties";

    private Log log;
    private ReadResourceProcessor readResourceProcessor;

    private StringProperty name;
    private StringProperty version;

    @Inject
    public MetaPropertiesProcessor(Log log, @ReadClasspathResource ReadResourceProcessor readResourceProcessor) {
        this.log = log;
        this.readResourceProcessor = readResourceProcessor;

        log.info(String.format("Initializing Processor<%s>...", this.getClass().getName()));

        this.name = new SimpleStringProperty("");
        this.version = new SimpleStringProperty("");
    }

    @Override
    @PostConstruct
    public void setUp() throws Exception {
        log.info(String.format("Setting up Processor<%s>...", this.getClass().getName()));

        this.initializeProperties();
    }

    @Override
    public void tearDown() throws Exception {
        this.readResourceProcessor.tearDown();
    }

    private void initializeProperties() throws IOException {
        log.debug(String.format("Initializing from classpath resource: %s", getPropertiesPath()));

        Properties properties = new Properties();
        this.readResourceProcessor.resourcePathProperty().setValue(getPropertiesPath());

        Reader reader = new StringReader(this.readResourceProcessor.readToString(Charset.defaultCharset()));
        properties.load(reader);

        this.setName(properties.getProperty("name", ""));
        this.setVersion(properties.getProperty("version", ""));
    }

    public String getName() {
        return name.get();
    }

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getVersion() {
        return version.get();
    }

    @Override
    public StringProperty versionProperty() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public static String getPropertiesPath() {
        return propertiesPath;
    }

    public static void setPropertiesPath(String propertiesPath) {
        MetaPropertiesProcessor.propertiesPath = propertiesPath;
    }
}
