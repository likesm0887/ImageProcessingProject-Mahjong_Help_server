package data;

import org.junit.Test;
import org.opencv.core.Core;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SampleTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    @Test
    public void sampleReadTest() throws IOException {
       Sample sample =new Sample();
       assertEquals(sample.mahjongCardsSample.get(0).chineseValue,"1條");
    }
}
