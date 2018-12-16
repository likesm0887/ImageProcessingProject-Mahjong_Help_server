package data;

import org.opencv.core.Mat;
import processUtility.Cutup;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class HandCard {
    private ArrayList<MahjongCard>handCard;

    public HandCard(Image img, int divnum) throws IOException
    {
        Cutup cutup =new Cutup();
        cutup.cutImage(img,1,divnum);
    }
    public void sethandCard(ArrayList<MahjongCard> hc)
    {
        this.handCard=hc;
    }
    public ArrayList<MahjongCard> getHandCard()
    {
        return handCard;
    }

}
