package processUtility;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.IOException;

public class ImagePathToMat {
    public static Mat readMatImage(String path) {
        Mat mat = Highgui.imread(path);
        return mat;
    }
}
