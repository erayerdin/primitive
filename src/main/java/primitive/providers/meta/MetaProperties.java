package primitive.providers.meta;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// todo doc
public class MetaProperties extends Properties {
    private volatile static MetaProperties instance;
    private volatile static String location = "primitive.meta.properties";

    protected volatile Log log;
    private ReadOnlyStringProperty crName;
    private ReadOnlyStringProperty hrName;
    private ReadOnlyStringProperty version;

    private MetaProperties() throws IOException {
        log = LogFactory.getLog(this.getClass());
        log.info("Initializing MetaProperties...");

        log.debug(String.format("Checking %s file...", location));
        InputStream propertiesStream = this.getClass().getResourceAsStream("/"+MetaProperties.getLocation());
        this.load(propertiesStream);

        String crName = this.getProperty("crName");
        String hrName = this.getProperty("hrName");
        String version = this.getProperty("version");

        this.crName = new SimpleStringProperty(null);
        this.hrName = new SimpleStringProperty(null);
        this.version = new SimpleStringProperty(null);

        if (crName == null) {
            log.warn("crName property not found.");
        } else {
            this.crName = new SimpleStringProperty(crName);
        }

        if (hrName == null) {
            log.warn("hrName property not found.");
        } else {
            this.hrName = new SimpleStringProperty(hrName);
        }

        if (version == null) {
            log.warn("version property not found.");
        } else {
            this.version = new SimpleStringProperty(version);
        }
    }

    public static MetaProperties getInstance() throws IOException {
        if (instance == null)
            instance = new MetaProperties();

        return instance;
    }

    // todo doc for getters and setters

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        MetaProperties.location = location;
    }

    public String getCrName() {
        return crName.get();
    }

    public ReadOnlyStringProperty crNameProperty() {
        return crName;
    }

    public String getHrName() {
        return hrName.get();
    }

    public ReadOnlyStringProperty hrNameProperty() {
        return hrName;
    }

    public String getVersion() {
        return version.get();
    }

    public ReadOnlyStringProperty versionProperty() {
        return version;
    }
}
