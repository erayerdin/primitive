package primitive.providers.os;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import primitive.concurrent.BaseTask;
import primitive.providers.BaseProvider;

class OperatingSystemTask extends BaseTask<Object[]> {
    @Override
    protected Object[] call() throws Exception {
        Object[] info = new Object[3];

        String name = System.getProperty("os.name").toLowerCase();

        if (name.contains("windows")) {
            info[0] = OperatingSystem.WINDOWS;
        } else if (name.contains("linux")) {
            info[0] = OperatingSystem.LINUX;
        } else if (name.contains("os x")) {
            info[0] = OperatingSystem.OSX;
        } else {
            info[0] = OperatingSystem.OTHER;
        }

        info[1] = System.getProperty("os.version");
        info[2] = System.getProperty("os.arch");

        return info;
    }
}

// todo doc
public class OperatingSystemProvider extends BaseProvider {
    // todo doc
    private volatile static OperatingSystemProvider instance;

    private ReadOnlyObjectProperty<OperatingSystem> type;
    private ReadOnlyStringProperty version;
    private ReadOnlyStringProperty arch;

    private OperatingSystemProvider() {
        super();
    }

    protected void init() {
        this.type = new SimpleObjectProperty<OperatingSystem>(OperatingSystem.OTHER);
        this.version = new SimpleStringProperty(null);
        this.arch = new SimpleStringProperty(null);

        Task<Object[]> task = new OperatingSystemTask();

        task.setOnSucceeded(v -> {
            Object[] info = (Object[]) v.getSource().getValue();

            this.type = new SimpleObjectProperty<OperatingSystem>((OperatingSystem) info[0]);
            this.version = new SimpleStringProperty((String) info[1]);
            this.arch = new SimpleStringProperty((String) info[2]);
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

    public OperatingSystem getType() {
        return type.get();
    }

    public ReadOnlyObjectProperty<OperatingSystem> typeProperty() {
        return type;
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
