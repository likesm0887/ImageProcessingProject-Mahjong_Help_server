package data;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.highgui.Highgui;
import processUtility.ImagePathToMat;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HandCardTest {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    @Test
    public void handCardCompareToSampleTest() throws IOException {
        Image image = Toolkit.getDefaultToolkit().getImage("test_data/server.bmp");

        Highgui.imwrite("MatImage" + "test" + ".jpg",ImagePathToMat.readMatImage("test_data/server3.bmp"));

        HandCard hc = new HandCard(image,16);
        //
       for(int i =0;i<hc.handCardByString().size();i++)
       {
           System.out.println(hc.handCardByString().get(i));
       }

        assertEquals(hc.handCardByString().get(0),"7W");
    }
}
