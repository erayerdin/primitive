/*
 * Copyright 2018 Eray Erdin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package primitive.io.path.appdata;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.apache.commons.logging.Log;
import primitive.system.os.OperatingSystem;
import primitive.system.os.OperatingSystemType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

// todo doc
@Singleton
public class ApplicationDataProvider implements Provider<Path> {
    private volatile static Path appDataPath = null;

    private Log log;
    private OperatingSystem operatingSystem;
    private Properties metaProperties;

    @Inject
    public ApplicationDataProvider(Log log, OperatingSystem operatingSystem, Properties metaProperties) {
        this.log = log;
        this.operatingSystem = operatingSystem;
        this.metaProperties = metaProperties;
    }

    @Override
    public Path get() {
        log.debug("Providing application data directory...");

        if (appDataPath != null)
            return appDataPath;

        Path path = null;

        OperatingSystemType type = this.operatingSystem.getType();
        switch (type) {
            case WINDOWS:
                path = this.generateAppDataForWindows();
                break;
            case LINUX:
                path = this.generateAppDataForLinux();
                break;
            case OSX:
                path = this.generateAppDataForOSX();
                break;
            default:
                break;
        }

        appDataPath = path;

        return path;
    }

    private Path generateAppDataForOSX() {
        throw new UnsupportedOperationException("Not implemented yet!"); // todo implement osx
    }

    private Path generateAppDataForLinux() {
        log.debug("Generating application data directory for Linux...");

        String home = System.getProperty("user.home");
        String appname = this.metaProperties.getProperty("name");

        Path path = Paths.get(home, ".config", appname);

        if (Files.notExists(path)) {
            log.warn("Application data directory does not exist.");
            log.info("Creating application data directory.");
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                log.error(String.format("Could not create application data directory: %s", path), e);
            }
        }

        return path;
    }

    private Path generateAppDataForWindows() {
        throw new UnsupportedOperationException("Not implemented yet!"); // todo implement windows
    }
}
