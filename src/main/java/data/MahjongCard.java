package data;
import org.opencv.core.Mat;
public class MahjongCard {
    public String value;
    public Mat pic;
    public String chineseValue;
    public MahjongCard(String value , Mat pic )
    {
        this.value=value;
        this.pic=pic;
        if(value.charAt(1)=="W".charAt(0))
        {
            chineseValue=value.charAt(0)+"萬";
        }
        if(value.charAt(1)=="T".charAt(0))
        {
            chineseValue=value.charAt(0)+"條";
        }
        if(value.charAt(1)=="O".charAt(0))
        {
            chineseValue=value.charAt(0)+"筒";
        }
        if(value.equals("DONG"))
        {
            chineseValue="東";
        }
        if(value.equals("XI"))
        {
            chineseValue="西";
        }
        if(value.equals("NAN"))
        {
            chineseValue="南";
        }
        if(value.equals("BEI"))
        {
            chineseValue="北";
        }
        if(value.equals("ZHONG"))
        {
            chineseValue="中";
        }
        if(value.equals("FA"))
        {
            chineseValue="發";
        }
        if(value.equals("BAI"))
        {
            chineseValue="白";
        }
    }
}
