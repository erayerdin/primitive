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

package primitive.system.os;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.apache.commons.logging.Log;

// todo doc
@Singleton
public class OperatingSystemProvider implements Provider<OperatingSystem> {
    private volatile static OperatingSystem operatingSystem = null;

    private Log log;

    @Inject
    public OperatingSystemProvider(Log log) {
        this.log = log;
    }

    @Override
    public OperatingSystem get() {
        log.debug("Providing operating system...");

        if (operatingSystem != null) {
            return operatingSystem;
        }

        OperatingSystem os = new OperatingSystem();

        this.generateType(os);

        String version, arch;

        log.debug("Generating operating system version...");
        version = System.getProperty("os.version");
        os.setVersion(version);
        log.info(String.format("Current operating system version: %s", version));

        log.debug("Generating JVM architecture...");
        arch = System.getProperty("os.arch");
        os.setArch(arch);
        log.info(String.format("Current JVM Architecture: %s", arch));

        operatingSystem = os;

        return os;
    }

    private void generateType(OperatingSystem os) {
        log.debug("Generating operating system type...");
        String name = System.getProperty("os.name").toLowerCase();

        if (name.contains("linux"))
            os.setType(OperatingSystemType.LINUX);
        else if (name.contains("windows"))
            os.setType(OperatingSystemType.WINDOWS);
        else if (name.contains("os x"))
            os.setType(OperatingSystemType.OSX);
        else
            os.setType(OperatingSystemType.UNDEFINED);

        log.info(String.format("Current operating system type: %s", os.getType().getName()));
    }
}
