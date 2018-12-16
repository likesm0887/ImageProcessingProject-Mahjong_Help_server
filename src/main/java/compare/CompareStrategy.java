package compare;

import org.opencv.core.Mat;

public interface CompareStrategy
{
    public boolean   compare(Mat templateImage, Mat originalImage);

}
