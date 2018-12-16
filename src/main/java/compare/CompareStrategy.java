package compare;

import org.opencv.core.Mat;

public interface CompareStrategy
{
    public void  compare(Mat templateImage, Mat originalImage);

}
