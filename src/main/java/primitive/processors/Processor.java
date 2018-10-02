package primitive.processors;

import java.io.IOException;

public interface Processor {
    void setUp() throws IOException;
    void tearDown();
}
