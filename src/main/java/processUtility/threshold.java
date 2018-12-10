package processUtility;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public  class threshold {

    public static Mat threstholdImg(Mat image)
    {


        //建立灰度图像存储空间
        Mat dst=new Mat(image.rows(),image.cols(),CvType.CV_8UC1);
        //彩色图像灰度化
        Imgproc.cvtColor(image, dst,Imgproc.COLOR_RGB2GRAY);
        //保存灰度图像
        Highgui.imwrite("./Result/gray.jpg", dst);
        //建立图像二值化存储空间
        Mat B_img=new Mat(image.rows(),image.cols(), CvType.CV_8UC1);
        //图像二值化
        Imgproc.threshold(dst,B_img,50,255, Imgproc.THRESH_TOZERO);
        //图像保存输出
        return B_img;

    }
}
