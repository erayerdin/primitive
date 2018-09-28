package primitive.processors.system;

import javafx.beans.property.StringProperty;
import primitive.processors.Processor;

// todo doc
public interface VirtualMachineProcessor extends Processor {
    // todo doc
    String getArch();
    // todo doc
    StringProperty archProperty();
}
