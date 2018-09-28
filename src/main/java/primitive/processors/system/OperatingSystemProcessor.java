package primitive.processors.system;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import primitive.processors.Processor;

// todo doc
public interface OperatingSystemProcessor extends Processor {
    // todo doc
    OperatingSystemType getType();
    // todo doc
    ObjectProperty<OperatingSystemType> typeProperty();
    // todo doc
    String getVersion();
    // todo doc
    StringProperty versionProperty();
}
