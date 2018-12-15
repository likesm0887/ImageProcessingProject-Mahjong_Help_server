package data;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SampleTest {

    @Test
    public void sampleReadTest() throws IOException {
       Sample sample =new Sample();
       sample.readTxt();
       assertEquals(sample.mahjongCardsTempForString.get(0),"1條,1T,1T.jpg");
    }
}
