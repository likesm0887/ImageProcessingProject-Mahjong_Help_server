package processUtility;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

public class Cutup {
    private  int temp=0;
    private ArrayList<Mat> imageMat = new ArrayList<Mat>();
    public Cutup() throws IOException {
    }

    public  void imgToMat(Image[][] images) throws IOException {
        for (Image[] images2 : images)
            for (Image image2 : images2) {
                BufferedImage bi = ImgToBufImg.toBufferedImage(images2[temp]);
                Mat mat = BufImgToMat.BufferedImage2Mat(bi);
                imageMat.add(mat);
                temp++;

            }
    }
    public  ArrayList<Mat> cutImage(Image image, int rows, int cols) throws IOException {
        int x = 0, y = 0;

        BufferedImage bi = ImgToBufImg.toBufferedImage(image);
        int width = bi.getWidth();
        int height = bi.getHeight();
        int cutWidth = width / cols;
        int cutHeight = height / rows;
        int changeX = width / cols;
        int changeY = height;

        if (width > 2000) {
            width = 1000;
            height = bi.getHeight() * 1000 / bi.getWidth();
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);//缩小
            cutWidth = width / cols;
            cutHeight = height / rows;
            changeX = width / cols;
            changeY = height;
        }

        Image[][] images = new Image[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ImageFilter filter = new CropImageFilter(x + j * changeX, y + i * changeY, cutWidth, cutHeight);

                images[i][j] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), filter));
                imgToMat(images);
            }
        }
        return imageMat;
    }
}