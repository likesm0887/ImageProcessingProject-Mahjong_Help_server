package data;

import org.junit.Test;
import org.opencv.core.Core;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HandCardTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    @Test
    public void handCardCompareToSampleTest() throws IOException {
        Image image = Toolkit.getDefaultToolkit().getImage("server.bmp");
        HandCard hc = new HandCard(image,16);
        assertEquals(hc.getHandCard().get(0).value,"6w");


    }
}
