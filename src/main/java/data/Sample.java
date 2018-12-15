package data;

import com.sun.xml.internal.bind.v2.TODO;
import mahjongFactory.MahjongFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sample {
    public ArrayList<String>mahjongCardsTempForString  =new ArrayList<>();
    private List<MahjongCard>mahjongCards;
    public void readTxt() throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream("sample.txt"),"big5"));
        while (br.ready()) {
            try {
                //System.out.println(br.readLine());
                mahjongCardsTempForString.add(br.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        br.close();
    }

    //Todo create mat in factory
    public  void createSample()
    {
        MahjongFactory mf = new MahjongFactory();
        //mf.create(mahjongCardsTempForString);
    }

}
