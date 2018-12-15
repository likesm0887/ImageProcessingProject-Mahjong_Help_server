package data;
import org.opencv.core.Mat;
public class MahjongCard {
    public String value;
    public Mat pic;
    public MahjongCard(String value , Mat pic)
    {
        this.value=value;
        this.pic=pic;
    }
}
