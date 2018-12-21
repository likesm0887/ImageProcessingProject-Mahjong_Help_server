package data;

import mahjongFactory.MahjongFactory;
import org.opencv.core.Mat;
import processUtility.ImagePathToMat;

import java.io.IOException;
import java.util.ArrayList;

public class DifferenceSample  extends Sample
{


    public DifferenceSample() throws IOException {
        readTxt();
        createSample();
    }
    @Override
    protected void spliteMahjongCardsTempForString(String readString)
    {
        mahjongCardsForString.add(readString.split(",")[1]);
        String imgPath=readString.split(",")[2];
        mahjongPic.add(ImagePathToMat.readMatImage("sample/difference sample/"+imgPath+".jpg"));
    }



}
