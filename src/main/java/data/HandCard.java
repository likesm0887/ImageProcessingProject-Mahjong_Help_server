package data;

import compare.CompareStrategy;
import compare.FindFeaturePointStrategy;
import mahjong.IMajong;
import mahjong.majing;
import mahjongFactory.MahjongFactory;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import processUtility.Cutup;
import processUtility.ImagePathToMat;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public   class HandCard {
    private final String compareStrategyOption ="";
    private ArrayList<MahjongCard>handCard =new ArrayList<>();
    private ArrayList<Mat>handCardPic;
    private Sample sample;
    private ArrayList<String> handCardByString=new ArrayList<>();
    private CompareStrategy compareStrategy ;

    public  HandCard(Image img, int divnum) throws IOException
    {
        Cutup cutup =new Cutup();

        compareStrategy =new FindFeaturePointStrategy();
        sample=new FeaturePointSample();

        handCardPic=cutup.cutImage(img,1,divnum);
        matListToImage(handCardPic);
        handCardCompareToSample();
    }
    public static void matListToImage( ArrayList<Mat> imageMat)
    {
        for (int i =0;i<imageMat.size();i++)
        {
            Highgui.imwrite("MatImage" + i + ".jpg", imageMat.get(i));
        }
    }
    public ArrayList<MahjongCard> getHandCard()
    {
        return handCard;
    }
    private void handCardCompareToSample()
    {
        handCard= compareStrategy.compare(sample,handCardPic);
        setHandCardByString();
    }
    private  void setHandCardByString()
    {
        for (MahjongCard  mc: handCard)
        handCardByString.add(mc.value);
    }
    public List<String> handCardByString()
    {
        return handCardByString;
    }
    public List<String>getListenHard()
    {
        IMajong majong =new majing();
        majong.input(handCardByString);
        return majong.getListen();
    }

}
