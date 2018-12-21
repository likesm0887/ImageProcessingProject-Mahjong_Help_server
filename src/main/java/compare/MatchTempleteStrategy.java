package compare;

import data.MahjongCard;
import data.Sample;
import mahjongFactory.MahjongFactory;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class MatchTempleteStrategy implements CompareStrategy{
    static int count=0;
    private MahjongFactory mf;
    @Override
    public boolean compare(Mat templateImage, Mat originalImage)
    {
        return matchImg(templateImage,originalImage);
    }
    public ArrayList<MahjongCard>  compare(Sample sample , List< Mat> handCardPic)
    {
        ArrayList<MahjongCard> handCard =new ArrayList<>();
        for (int i =0 ;i<handCardPic.size();i++)
        {
            for(int j =0 ;j<sample.mahjongCardsSample.size();j++)
            {
                System.out.println(sample.mahjongCardsSample.get(j).chineseValue);
                if(matchImg( sample.mahjongCardsSample.get(j).pic,handCardPic.get(i)))
                {

                    handCard.add(mf.create(sample.mahjongCardsSample.get(j).value,sample.mahjongCardsSample.get(j).pic));
                    Highgui.imwrite("output/" + i+"H" + ".jpg",handCardPic.get(i));
                    Highgui.imwrite("output/" + i + ".jpg",sample.mahjongCardsSample.get(j).pic);

                    break;
                }
            }
        }
        return handCard;
    }
    public  boolean matchImg(Mat template, Mat source)
    {
        //將文檔讀入為OpenCV的Mat格式
        //創建於原圖相同的大小，儲存匹配度
       try
       {
           Mat result = Mat.zeros(source.rows() - template.rows() + 1, source.cols() - template.cols() + 1, CvType.CV_32FC1);
           //調用模板匹配方法
           Imgproc.matchTemplate(source, template, result, Imgproc.TM_SQDIFF);
           //規格化
           Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1);
           //獲得最可能點，MinMaxLocResult是其數據格式，包括了最大、最小點的位置x、y
           Core.MinMaxLocResult mlr = Core.minMaxLoc(result);
           Point matchLoc = mlr.minLoc;
           //在原圖上的對應模板可能位置畫一個綠色矩形
           Core.rectangle(source, matchLoc, new Point(matchLoc.x -20 + template.width(), matchLoc.y-20 + template.height()), new Scalar(0, 255, 0),1);

           Highgui.imwrite("C:\\Users\\user\\Documents\\MahJong_Help_Server\\"+count+".jpg", source);
           return true;
       }catch (Exception e)
       {
           return false;
       }


       // matchImg(template,  source);
        //將結果輸出到對應位置
    }
    public  int getCount()
    {
        return count;
    }


}
