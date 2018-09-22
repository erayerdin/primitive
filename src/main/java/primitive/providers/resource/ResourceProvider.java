package primitive.providers.resource;

import primitive.concurrent.BaseTask;
import primitive.providers.BaseProvider;
import primitive.providers.meta.MetaProvider;
import primitive.providers.os.OperatingSystemProvider;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class LocalAppDataTask extends BaseTask<Void> { // todo review task singleton pattern
    private ResourceProvider provider;

    public LocalAppDataTask(ResourceProvider provider) {
        this.provider = provider;
    }

    @Override
    protected Void call() throws Exception {
        Path localAppData, localVersionAppData;

        localAppData = this.provider.getApplicationDataDirectory(false, false);
        localVersionAppData = this.provider.getApplicationDataDirectory(false, true);

        if (Files.notExists(localAppData)) {
            log.debug("Creating local application data directory...");
            Files.createDirectories(localAppData);
        }

        if (Files.notExists(localVersionAppData)) {
            log.debug("Creating local version-based application data directory...");
            Files.createDirectories(localVersionAppData);
        }

        return null;
    }
}

class GlobalAppDataTask extends BaseTask<Void> {
    private ResourceProvider provider;

    public GlobalAppDataTask(ResourceProvider provider) {
        this.provider = provider;
    }

    @Override
    protected Void call() throws Exception {
        Path globalAppData, globalVersionAppData;

        globalAppData = this.provider.getApplicationDataDirectory(true, false);
        globalVersionAppData = this.provider.getApplicationDataDirectory(true, true);

        if (Files.notExists(globalAppData)) {
            log.debug("Creating global application data directory...");
            Files.createDirectories(globalAppData);
        }

        if (Files.notExists(globalVersionAppData)) {
            log.debug("Creating global version-based application data directory...");
            Files.createDirectories(globalVersionAppData);
        }

        return null;
    }
}

// todo doc
public class ResourceProvider extends BaseProvider {
    private volatile static ResourceProvider instance;

    private ResourceProvider() {
        super();
    }

    public static ResourceProvider getInstance() {
        if (instance == null)
            instance = new ResourceProvider();

        return instance;
    }

    // todo doc
    public Path getGlobalApplicationDataDirectory(boolean isVersionBased) {
        return this.getApplicationDataDirectory(true, isVersionBased);
    }

    // todo doc
    public Path getLocalApplicationDataDirectory(boolean isVersionBased) {
        return this.getApplicationDataDirectory(false, isVersionBased);
    }

    // todo doc
    public Path getApplicationDataDirectory(boolean isGlobal, boolean isVersionBased) {
        OperatingSystemProvider operatingSystemProvider = OperatingSystemProvider.getInstance();
        MetaProvider metaProvider = MetaProvider.getInstance();

        Path directory;

        switch (operatingSystemProvider.getType()) {
            case WINDOWS:
                if (isGlobal) {
                    directory = Paths.get(System.getenv("ProgramData"));
                } else {
                    directory = Paths.get("AppData");
                }
                break;
            case LINUX:
                directory = Paths.get(System.getenv("HOME"), ".config");
                break;
            // todo get dir for os x
            default:
                return null;
        }

        if (metaProvider.getCrName() != null) {
            directory = Paths.get(directory.toString(), metaProvider.getCrName());
        }

        if (metaProvider.getVersion() != null) {
            directory = Paths.get(directory.toString(), metaProvider.getVersion());
        }

        return directory;
    }

    // todo doc
    public InputStream getClasspathResourceStream(String file) {
        return this.getClass().getClassLoader().getResourceAsStream(file);
    }

    @Override
    protected void init() {
        BaseTask[] tasks = new BaseTask[2];
        tasks[0] = new LocalAppDataTask(this);
        tasks[1] = new GlobalAppDataTask(this);

        for (int i = 0; i < tasks.length; i++) {
            BaseTask task = tasks[i];
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    @Override
    protected void uninit() {}
}
