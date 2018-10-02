package primitive.processors.meta;

import javafx.beans.property.StringProperty;
import primitive.processors.Processor;

// todo doc
public interface MetaProcessor extends Processor {
    // todo doc
    StringProperty nameProperty();

    // todo doc
    StringProperty versionProperty();
}
