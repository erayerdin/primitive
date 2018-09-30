package primitive.processors.resource.listeners;

import javafx.beans.value.ObservableValue;
import primitive.listeners.BaseChangeListener;
import primitive.processors.resource.ReadResourceProcessor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceSizeListener extends BaseChangeListener<String> {
    private ReadResourceProcessor readResourceProcessor;

    public ResourceSizeListener(ReadResourceProcessor readResourceProcessor) {
        super();
        this.readResourceProcessor = readResourceProcessor;
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
        if (!newVal.equals(oldVal)) {
            log.info(String.format("Path of resource has been changed from %s to %s.", oldVal, newVal));
            boolean isClasspathResource = this.readResourceProcessor.getBasePath() == null;

            InputStream stream;
            if (isClasspathResource) {
                log.debug("Resource is a classpath resource.");
                stream = ClassLoader.getSystemResourceAsStream(newVal);
            } else {
                log.debug("Resource is an absolute resource.");
                Path absolutePath = Paths.get(this.readResourceProcessor.getBasePath().toString(), newVal);
                try {
                    stream = new FileInputStream(absolutePath.toFile());
                } catch (FileNotFoundException e) {
                    log.error(
                            String.format("Could not read the file in destination: %s", absolutePath.toString()),
                            e
                    );
                }
                return;
            }

            try {
                this.readResourceProcessor.totalWorkProperty().setValue(stream.available());
            } catch (IOException e) {
                log.error(
                        String.format("Could not determine the size of file in relative destination: %s", newVal),
                        e
                );
                return;
            }
        }
    }
}