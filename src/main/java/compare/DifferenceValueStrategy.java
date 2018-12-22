import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.StringTokenizer;

public class DifferenceValueStrategy implements CompareStrategy {

    @Override
    public ArrayList<MahjongCard>  compare(Sample sample , List< Mat> handCardPic)
    {
        ArrayList<MahjongCard> handCard =new ArrayList<>();
        MahjongFactory mf=new MahjongFactory();
        for (int i =0 ;i<handCardPic.size();i++)
        {
            for(int j =0 ;j<sample.mahjongCardsSample.size();j++)
            {
                System.out.println(sample.mahjongCardsSample.get(j).chineseValue);
                if(matchImage( sample.mahjongCardsSample.get(j).pic,handCardPic.get(i)))
                {
                    handCard.add(mf.create(sample.mahjongCardsSample.get(j).value,sample.mahjongCardsSample.get(j).pic));
                    Highgui.imwrite("output/" + i+"H" + ".jpg",handCardPic.get(i));
                    Highgui.imwrite("output/" + i + ".jpg",sample.mahjongCardsSample.get(j).pic);

                    break;
                }
            }
        }
        return handCard;
    }

    public boolean compare(Mat templete , Mat Origin)
    {
        return matchImage(templete,Origin);
    }


    public boolean matchImage(Mat templateImage, Mat originalImage) {
        BufferedImage templatebuf = matToBufferedImage(templateImage);
        BufferedImage originalbuf = matToBufferedImage(originalImage);
        String imgPath1 = imgToBase64String(templatebuf);
        String imgPath2 = imgToBase64String(originalbuf);

        int width1 = 100;
        int height1 = 180;
        int width2 = 100;
        int height2 = 180;

        String[] images = {imgPath1, imgPath2};
        if (images.length == 0) {
            System.out.println("Usage >java BMPLoader ImageFile.bmp");
            System.exit(0);
        }

        // 分析圖片相似度 begin

        String[][] list1 = getPX(templatebuf);
        String[][] list2 = getPX(originalbuf);

        int xiangsi = 0;
        int busi = 0;
        int m = 0, n = 0;
        String[] value1 = new String[54000];
        String[] value2 = new String[54000];

        for(int i = 0; i < width1; i++)
        {
            for(int j = 0; j < height1; j++)
            {
                StringTokenizer st = new StringTokenizer(list1[i][j],",");
                while (st.hasMoreTokens())
                {
                    value1[m] = st.nextToken();
                    m++;
                }
            }
        }
        for(int i = 0; i < width2; i++)
        {
            for(int j = 0; j < height2; j++)
            {
                StringTokenizer st = new StringTokenizer(list2[i][j],",");
                while (st.hasMoreTokens())
                {
                    value2[n] = st.nextToken();
                    n++;
                }
            }
        }
        int count = 0;

        for(int i = 0; i < value1.length; i++)
        {
            if (Math.abs(Integer.parseInt(value1[i]) - Integer.parseInt(value2[i])) < 20) {
                count = 0;
                xiangsi++;
            } else {
                count++;
                busi++;
            }
            if (count == 100)
            {
                xiangsi = xiangsi + 100;
                busi = Math.abs(busi - 100);
                count = 0;
            }
        }

        String baifen = "";
        try {
            baifen = ((Double.parseDouble(xiangsi + "") / Double.parseDouble((busi + xiangsi) + "")) + "");
            baifen = baifen.substring(baifen.indexOf(".") + 1, baifen.indexOf(".") + 3);
        } catch (Exception e) {
            baifen = "0";
        }
        if (baifen.length() <= 0) {
            baifen = "0";
        }
        if(busi == 0){
            baifen="100";
        }

        System.out.println("相似像素數量：" + xiangsi + " 不相似像素數量：" + busi + " 相似率：" + Integer.parseInt(baifen) + "%");

        if (Integer.parseInt(baifen) > 76)
        {
            return true;
        }else {
            System.out.println("配對失敗");
            return false;
        }

    }

    public BufferedImage matToBufferedImage(Mat matrix){
        int cols = matrix.cols();
        int rows = matrix.rows()
        int elemSize = (int)matrix.elemSize();
        byte[] data = new byte[cols*rows*elemSize];
        matrix.get(0, 0, data);
        BufferedImage bufImg = null;
        switch(matrix.channels())
        {
            case 1:
                bufImg = new BufferedImage(cols, rows, BufferedImage.TYPE_BYTE_GRAY);
                break;
            case 3:
                bufImg = new BufferedImage(cols,rows, BufferedImage.TYPE_3BYTE_BGR);
                break;
        }
        bufImg.getRaster().setDataElements(0, 0, cols, rows, data);
        return bufImg;
    }

    public String[][] getPX(BufferedImage bi)       // 改成二進制碼
    {
        int[] rgb = new int[3];

        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        String[][] list = new String[width][height];
        for (int i = minx; i < width; i++)
        {
            for (int j = miny; j < height; j++)
            {
                int pixel = bi.getRGB(i, j);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                list[i][j] = rgb[0] + "," + rgb[1] + "," + rgb[2];
            }
        }
        return list;
    }

    public String imgToBase64String(final RenderedImage img) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "jpg", Base64.getEncoder().wrap(os));
            return os.toString(StandardCharsets.ISO_8859_1.name());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }


}