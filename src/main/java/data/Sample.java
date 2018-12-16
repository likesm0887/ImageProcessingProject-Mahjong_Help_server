package data;

import com.sun.xml.internal.bind.v2.TODO;
import mahjongFactory.MahjongFactory;
import org.opencv.core.Mat;
import processUtility.ImagePathToMat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sample {
    public Sample() throws IOException {
        readTxt();
        createSample();
    }
    public ArrayList<String>mahjongCardsForString  =new ArrayList<>();
    public ArrayList<MahjongCard>mahjongCardsSample  =new ArrayList<>();
    private ArrayList<Mat>mahjongCardsPic  =new ArrayList<>();
    private List<MahjongCard>mahjongCards;
    private void readPic()
    {
        for(int i =0;i<34;i++)
        {
           // ImagePathToMat()
        }
    }
    private void readTxt() throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream("sample.txt"),"big5"));
        while (br.ready()) {
            try {
                //System.out.println(br.readLine());
                spliteMahjongCardsTempForString(br.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        br.close();
    }
    private void spliteMahjongCardsTempForString(String readString)
    {
        mahjongCardsForString.add(readString.split(",")[1]);
        String imgPath=readString.split(",")[2];
        mahjongCardsPic.add(ImagePathToMat.readMatImage(imgPath));
    }
    //Todo create mat in factory
    private   void createSample()
    {
        MahjongFactory mf = new MahjongFactory();
        mahjongCardsSample=mf.create(mahjongCardsForString,mahjongCardsPic);
    }

}
