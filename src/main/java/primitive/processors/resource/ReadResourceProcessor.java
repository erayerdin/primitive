package primitive.processors.resource;

import javafx.beans.property.LongProperty;
import javafx.beans.property.StringProperty;
import primitive.processors.Processor;

import java.nio.file.Path;

// todo doc
public interface ReadResourceProcessor extends Processor {
    // todo doc
    Path getBasePath();

    // todo doc
    StringProperty resourcePathProperty();

    // todo doc
    LongProperty totalWorkProperty();

    // todo doc
    LongProperty workDoneProperty();

    // todo doc
    byte[] readToBytes();

    // todo doc
    String readToString();
}