package mahjongFactory;

import data.MahjongCard;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

public class MahjongFactory {
    ///輸入數值和Pic 產生object
    public MahjongCard create(String value , Mat pic)
    {
            return new MahjongCard(value,pic);
    }

    //輸入數值List和PicList 產生objectList
    public ArrayList<MahjongCard> create(List<String> valueList ,List<Mat>  pic)
    {
        ArrayList<MahjongCard> mahjongCards = null;
        if(valueList.size()!=pic.size())
        {
            System.out.println("數值與數量不符合");
        }
        else {

            for (int i = 0; i < valueList.size(); i++) {
                mahjongCards.add(new MahjongCard(valueList.get(i), pic.get(i)));
            }
        }
        return mahjongCards;
    }
}
