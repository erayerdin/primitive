package primitive.providers.os;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import primitive.concurrent.BaseTask;
import primitive.providers.BaseProvider;

class OperatingSystemTask extends BaseTask<String[]> {
    @Override
    protected String[] call() throws Exception {
        String[] info = new String[3];

        info[0] = System.getProperty("os.name");
        info[1] = System.getProperty("os.version");
        info[2] = System.getProperty("os.arch");

        return info;
    }
}

// todo doc
public class OperatingSystemProvider extends BaseProvider {
    // todo doc
    private volatile static OperatingSystemProvider instance;

    private ReadOnlyStringProperty name;
    private ReadOnlyStringProperty version;
    private ReadOnlyStringProperty arch;

    private OperatingSystemProvider() {
        super();
    }

    protected void init() {
        this.name = new SimpleStringProperty(null);
        this.version = new SimpleStringProperty(null);
        this.arch = new SimpleStringProperty(null);

        Task<String[]> task = new OperatingSystemTask();

        task.setOnSucceeded(v -> {
            String[] info = (String[]) v.getSource().getValue();

            this.name = new SimpleStringProperty(info[0]);
            this.version = new SimpleStringProperty(info[1]);
            this.arch = new SimpleStringProperty(info[2]);
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    protected void uninit() {

    }

    public static OperatingSystemProvider getInstance() {
        if (instance == null)
            instance = new OperatingSystemProvider();

        return instance;
    }

    // todo doc for getters and setters

    public String getName() {
        return name.get();
    }

    public ReadOnlyStringProperty nameProperty() {
        return name;
    }

    public String getVersion() {
        return version.get();
    }

    public ReadOnlyStringProperty versionProperty() {
        return version;
    }

    public String getArch() {
        return arch.get();
    }

    public ReadOnlyStringProperty archProperty() {
        return arch;
    }
}
