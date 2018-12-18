package compare;

import data.MahjongCard;
import data.Sample;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

public interface CompareStrategy
{
    public ArrayList<MahjongCard> compare(Sample sample , List< Mat> handCard);
    public boolean compare(Mat templete , Mat Origin);
}
