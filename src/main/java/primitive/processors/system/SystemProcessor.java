package primitive.processors.system;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.logging.Log;
import primitive.concurrent.BaseTask;
import primitive.processors.system.concurrent.OperatingSystemTask;
import primitive.processors.system.concurrent.VirtualMachineTask;

import java.util.HashMap;

// todo doc
@Singleton
public class SystemProcessor implements OperatingSystemProcessor, VirtualMachineProcessor {
    private Log log;

    private volatile ObjectProperty<OperatingSystemType> type;
    private volatile StringProperty version;
    private volatile StringProperty arch;

    @Inject
    public SystemProcessor(Log log) {
        this.log = log;
        log.info(String.format("Initializing %s...", this.getClass().getName()));
        this.type = new SimpleObjectProperty<>(OperatingSystemType.UNKNOWN);
        this.version = new SimpleStringProperty(null);
        this.arch = new SimpleStringProperty(null);
    }

    @Override
    public void setUp() {
        log.info(String.format("Setting up %s...", this.getClass().getName()));
        this.initializeTasks();
    }

    @Override
    public void tearDown() {
        log.info(String.format("Tearing down %s...", this.getClass().getName()));
    }

    // todo doc
    private void initializeTasks() {
        log.info("Initializing tasks...");
        this.initializeOperatingSystemTask();
        this.initializeVirtualMachineTask();
    }

    // todo doc
    private void initializeOperatingSystemTask() {
        BaseTask<HashMap<String, Object>> task = new OperatingSystemTask();
        task.setOnSucceeded(v -> {
            BaseTask<HashMap<String, Object>> source = (BaseTask<HashMap<String, Object>>) v.getSource();
            this.setType((OperatingSystemType) source.getValue().get("type"));
            this.setVersion((String) source.getValue().get("version"));
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    // todo doc
    private void initializeVirtualMachineTask() {
        BaseTask<String> task = new VirtualMachineTask();
        task.setOnSucceeded(v -> {
            BaseTask<String> source = (BaseTask<String>) v.getSource();
            this.setArch(source.getValue());
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    @Override
    public OperatingSystemType getType() {
        return type.get();
    }

    public ObjectProperty<OperatingSystemType> typeProperty() {
        return type;
    }

    public void setType(OperatingSystemType type) {
        this.type.set(type);
    }

    @Override
    public String getVersion() {
        return version.get();
    }

    public StringProperty versionProperty() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    @Override
    public String getArch() {
        return arch.get();
    }

    public StringProperty archProperty() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch.set(arch);
    }
}
