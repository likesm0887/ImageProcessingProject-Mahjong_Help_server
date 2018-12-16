package data;

import compare.CompareStrategy;
import compare.FindFeaturePointStrategy;
import mahjong.IMajong;
import mahjong.majing;
import mahjongFactory.MahjongFactory;
import org.opencv.core.Mat;
import processUtility.Cutup;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HandCard {
    private final String compareStrategyOption ="";
    private ArrayList<MahjongCard>handCard;
    private Sample sample;
    private ArrayList<String> handCardByString;
    private CompareStrategy compareStrategy ;
    private MahjongFactory mf;
    public HandCard(Image img, int divnum) throws IOException
    {
        Cutup cutup =new Cutup();
        cutup.cutImage(img,1,divnum);
        compareStrategy =new FindFeaturePointStrategy();
        mf=new MahjongFactory();
        sample=new Sample();
        handCardCompareToSample();
    }

    public void sethandCard(ArrayList<MahjongCard> hc)
    {
        this.handCard=hc;
    }
    public ArrayList<MahjongCard> getHandCard()
    {
        return handCard;
    }
    private void handCardCompareToSample()
    {
        for (int i =0 ;i<handCard.size();i++)
        {
            for(int j =0 ;j<sample.mahjongCardsSample.size();j++)
            {
                if(compareStrategy.compare(handCard.get(i).pic, sample.mahjongCardsSample.get(i).pic))
                {
                    handCard.add(mf.create(sample.mahjongCardsSample.get(i).value,sample.mahjongCardsSample.get(i).pic));
                    handCardByString.add( sample.mahjongCardsSample.get(i).value);
                }
            }
        }
    }
    public List<String>getListenHard()
    {
        IMajong majong =new majing();
        majong.input(handCardByString);
        return majong.getListen();
    }

}
