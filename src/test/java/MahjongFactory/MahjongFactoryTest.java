package MahjongFactory;

import data.MahjongCard;
import mahjongFactory.MahjongFactory;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import processUtility.ImagePathToMat;

import static org.junit.Assert.assertEquals;

public class MahjongFactoryTest {
    @Before
    public  void setup()
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    @Test
    public void TestMahjongFactoryCreate()
    {
        MahjongFactory mf = new MahjongFactory();
        MahjongCard mc ;
        Mat mat = ImagePathToMat.readMatImage("./4s2.JPG");
        mc= mf.create("4w",mat);
        assertEquals("4w",mc.value);
        assertEquals(mat,mc.pic);

    }

}
