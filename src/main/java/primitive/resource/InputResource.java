package primitive.resource;

import java.io.InputStream;

// todo doc
public interface InputResource {
    // todo doc
    InputStream getInputStream();

    // todo doc
    String getPath();

    // todo doc
    void setPath(String path);
}
