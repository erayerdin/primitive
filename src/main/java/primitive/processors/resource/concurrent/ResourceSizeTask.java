package primitive.processors.resource.concurrent;

import primitive.concurrent.BaseTask;

import java.io.InputStream;

public class ResourceSizeTask extends BaseTask<Integer> {
    private InputStream stream;

    public ResourceSizeTask(InputStream stream) {
        super();
        this.stream = stream;
    }

    @Override
    protected Integer call() throws Exception {
        Integer size = stream.available();
        return size;
    }
}
