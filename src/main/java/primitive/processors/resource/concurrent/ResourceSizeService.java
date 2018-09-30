package primitive.processors.resource.concurrent;

import javafx.concurrent.Task;
import primitive.concurrent.BaseService;

import java.io.InputStream;

// todo doc
public class ResourceSizeService extends BaseService<Integer> {
    private InputStream stream;

    public ResourceSizeService(InputStream stream) {
        this.stream = stream;
    }

    @Override
    protected Task<Integer> createTask() {
        return new ResourceSizeTask(this.stream);
    }
}
