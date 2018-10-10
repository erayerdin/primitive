/*
 * Copyright 2018 Eray Erdin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package primitive.resource.input;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Arrays;

// todo doc
public class BaseInputStreamReader extends InputStreamReader {
    private IntegerProperty workDone;
    private IntegerProperty totalWork;


    public BaseInputStreamReader(InputStream in) throws IOException {
        super(in);

        this.workDone = new SimpleIntegerProperty(0);
        this.totalWork = new SimpleIntegerProperty(in.available());
    }

    public BaseInputStreamReader(InputStream in, String charsetName) throws IOException {
        super(in, charsetName);

        this.workDone = new SimpleIntegerProperty(0);
        this.totalWork = new SimpleIntegerProperty(in.available());
    }

    public BaseInputStreamReader(InputStream in, Charset cs) throws IOException {
        super(in, cs);

        this.workDone = new SimpleIntegerProperty(0);
        this.totalWork = new SimpleIntegerProperty(in.available());
    }

    public BaseInputStreamReader(InputStream in, CharsetDecoder dec) throws IOException {
        super(in, dec);

        this.workDone = new SimpleIntegerProperty(0);
        this.totalWork = new SimpleIntegerProperty(in.available());
    }

    @Override
    public int read() throws IOException {
        int data = super.read();

        if (data != -1) // todo a problem here
            this.workDone.set(this.workDone.get()+1);

        return data;
    }

    @Override
    public int read(char[] cbuf, int offset, int length) throws IOException {
        int data = super.read(cbuf, offset, length);

        int len = 0;
        for (char c : cbuf) {
            int cint = (int) c;

            if (cint > 0)
                len++;
        }

        if (data != -1)
            this.workDone.set(this.workDone.get()+len);

        return data;
    }

    @Override
    public void close() throws IOException {
        this.workDone.set(this.totalWork.get());
        super.close();
    }

    // todo doc for getters and setters

    public ReadOnlyIntegerProperty workDoneProperty() {
        return workDone;
    }

    public ReadOnlyIntegerProperty totalWorkProperty() {
        return totalWork;
    }
}
