import compare.CompareStrategy;
import org.junit.Test;
import org.opencv.core.Core;
import compare.FindFeaturePointStrategy;
import processUtility.ImagePathToMat;
import compare.MatchTempleteStrategy;
import static org.junit.Assert.assertEquals;

public class matchTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    @Test
    public void TestMatch()
    {
        CompareStrategy m =new MatchTempleteStrategy();

        //m.matchImg( ImagePathToMat.readMatImage("MatImage0.jpg"),ImagePathToMat.readMatImage("MatImage10.jpg"));

        //m.compare(ImagePathToMat.readMatImage("sample/feature_point/1W.jpg"),ImagePathToMat.readMatImage("MatImage9.jpg"));
        CompareStrategy ff = new FindFeaturePointStrategy();
        ff.compare(ImagePathToMat.readMatImage("sample/feature_point/1O.jpg"),ImagePathToMat.readMatImage("MatImage0.jpg"));
       // ff.compare(ImagePathToMat.readMatImage("6ss.jpg"),ImagePathToMat.readMatImage("MatImage12.jpg"));
    }
}
