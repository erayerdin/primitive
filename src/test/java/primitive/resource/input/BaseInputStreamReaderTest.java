package primitive.resource.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BaseInputStreamReaderTest {
    private BaseInputStreamReader reader;

    @Before
    public void setUp() throws Exception {
        InputStream stream = this.getClass().getResourceAsStream("/random.txt");
        this.reader = new BaseInputStreamReader(stream);
    }

    @Test
    public void testTotalWork() {
        assertThat(this.reader.totalWorkProperty().greaterThan(0).get(), is(true));
    }

    @Test
    public void testWorkDoneByReadNoArgs() throws IOException {
        assertThat(this.reader.workDoneProperty().get(), is(equalTo(0)));
        this.reader.read();
        assertThat(this.reader.workDoneProperty().get(), is(equalTo(1)));
    }

    @Test
    public void testBufferReadNoArgs() throws IOException {
        assertThat(this.reader.workDoneProperty().get(), is(equalTo(0)));

        BufferedReader buffer = new BufferedReader(this.reader);
        buffer.read();
        assertThat(this.reader.workDoneProperty().greaterThan(1).get(), is(true));
    }

    @Test
    public void testBufferReadLine() throws IOException {
        assertThat(this.reader.workDoneProperty().get(), is(equalTo(0)));

        BufferedReader buffer = new BufferedReader(this.reader);
        buffer.readLine();
        assertThat(this.reader.workDoneProperty().get(), is(equalTo(6)));
    }

    @After
    public void tearDown() throws Exception {
        this.reader.close();
    }
}