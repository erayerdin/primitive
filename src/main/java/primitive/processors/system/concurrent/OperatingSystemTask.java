package primitive.processors.system.concurrent;

import primitive.concurrent.BaseTask;
import primitive.processors.system.OperatingSystemType;

import java.util.HashMap;

// todo doc
public class OperatingSystemTask extends BaseTask<HashMap<String, Object>> {
    @Override
    protected HashMap<String, Object> call() throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        // Operating System Type
        OperatingSystemType type = this.getOperatingSystemType();

        log.debug("Getting version of operating system...");
        String version = System.getProperty("os.version");
        log.info(String.format("Current operating system version is: %s", version));

        result.put("type", type);
        result.put("version", version);

        return result;
    }

    private OperatingSystemType getOperatingSystemType() {
        log.debug("Getting type of operating system...");
        OperatingSystemType type = OperatingSystemType.UNKNOWN;

        String name = System.getProperty("os.name").toLowerCase();

        if (name.contains("windows"))
            type = OperatingSystemType.WINDOWS;
        else if (name.contains("linux"))
            type = OperatingSystemType.LINUX;
        else if (name.contains("os x"))
            type = OperatingSystemType.OSX;

        log.info(String.format("Current operating system type is: %s", type.getName()));

        return type;
    }
}
