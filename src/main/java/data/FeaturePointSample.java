package data;

import mahjongFactory.MahjongFactory;
import org.opencv.highgui.Highgui;
import processUtility.ImagePathToMat;

import java.io.IOException;

public class FeaturePointSample extends Sample {

    public FeaturePointSample() throws IOException
    {


    }
    @Override
    protected void spliteMahjongCardsTempForString(String readString)
    {
        mahjongCardsForString.add(readString.split(",")[1]);
        String imgPath=readString.split(",")[2];
        mahjongPic.add(ImagePathToMat.readMatImage("sample/Feature_point/"+imgPath));
    }

}
