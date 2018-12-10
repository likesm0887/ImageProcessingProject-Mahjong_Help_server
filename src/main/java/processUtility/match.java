package processUtility;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class match {
    static int count=0;
    public  void matchImg(Mat template, Mat source)
    {
        //將文檔讀入為OpenCV的Mat格式
        //創建於原圖相同的大小，儲存匹配度
       try
       {
           if(count>=3) return;
           Mat result = Mat.zeros(source.rows() - template.rows() + 1, source.cols() - template.cols() + 1, CvType.CV_32FC1);
           //調用模板匹配方法
           Imgproc.matchTemplate(source, template, result, Imgproc.TM_SQDIFF);
           //規格化
           Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
           //獲得最可能點，MinMaxLocResult是其數據格式，包括了最大、最小點的位置x、y
           Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
           Point matchLoc = mlr.minLoc;
           //在原圖上的對應模板可能位置畫一個綠色矩形
           Core.rectangle(source, matchLoc, new Point(matchLoc.x -20 + template.width(), matchLoc.y-20 + template.height()), new Scalar(0, 255, 0),-1);

           Highgui.imwrite("C:\\Users\\user\\Documents\\MahJong_Help_Server\\"+count+".jpg", source);
           count++;
       }catch (Exception e)
       {
           return;
       }


        matchImg(template,  source);
        //將結果輸出到對應位置
    }
    public  int getCount()
    {
        return count;
    }

}
