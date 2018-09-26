package primitive.providers.os;

import primitive.providers.BaseProvider;

import java.util.HashMap;

// todo doc
public interface OperatingSystemProvider extends BaseProvider {
    HashMap<String, Object> getInformation();
}
