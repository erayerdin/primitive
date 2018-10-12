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

package primitive.meta;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// todo doc
@Singleton
public class PropertiesProvider implements Provider<Properties> {
    private volatile static Properties meta = null;

    private Log log;

    @Inject
    public PropertiesProvider(Log log) {
        this.log = log;
    }

    @Override
    public Properties get() {
        log.debug("Providing meta properties...");

        if (meta != null)
            return meta;

        Properties properties = new Properties();
        this.loadProperties(properties);

        meta = properties;

        return meta;
    }

    private void loadProperties(Properties properties) {
        log.debug("Loading meta properties from primitive.meta.properties...");

        InputStream stream = this.getClass().getResourceAsStream("/primitive.meta.properties");

        try {
            properties.load(stream);
        } catch (IOException e) {
            log.error("An error occured while loading primitive.meta.properties.", e);
        }
    }
}
