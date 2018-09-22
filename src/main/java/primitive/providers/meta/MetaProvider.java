package primitive.providers.meta;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import primitive.concurrent.BaseTask;
import primitive.providers.BaseProvider;

class MetaTask extends BaseTask<MetaProperties> {
    @Override
    protected MetaProperties call() throws Exception {
        return MetaProperties.getInstance();
    }
}

// todo doc
public class MetaProvider extends BaseProvider {
    private volatile static MetaProvider instance;

    private ReadOnlyStringProperty crName;
    private ReadOnlyStringProperty hrName;
    private ReadOnlyStringProperty version;

    private MetaProvider() {
        super();
    }

    protected void init() {
        this.crName = new SimpleStringProperty(null);
        this.hrName = new SimpleStringProperty(null);
        this.version = new SimpleStringProperty(null);

        BaseTask<MetaProperties> task = new MetaTask();

        task.setOnSucceeded(v -> {
            MetaProperties properties = (MetaProperties) v.getSource().getValue();

            this.crName = properties.crNameProperty();
            this.hrName = properties.hrNameProperty();
            this.version = properties.versionProperty();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    protected void uninit() {}

    public static MetaProvider getInstance() {
        if (instance == null)
            instance = new MetaProvider();

        return instance;
    }

    // todo doc for getters and setters


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
