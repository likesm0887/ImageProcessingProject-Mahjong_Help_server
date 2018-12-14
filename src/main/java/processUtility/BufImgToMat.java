package processUtility;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class BufImgToMat {

    BufferedImage original;
    int itype;
    int mtype;

    /**
     *
     * @param image
     * @param imgType bufferedImage的類型 如 BufferedImage.TYPE_3BYTE_BGR
     * @param matType 轉換成mat的type 如 CvType.CV_8UC3
     */

    public BufImgToMat(BufferedImage image, int imgType, int matType)
    {
        original = image;
        itype = imgType;
        mtype = matType;
    }

    public static Mat BufferedImage2Mat(BufferedImage image) throws IOException
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Highgui.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Highgui.CV_LOAD_IMAGE_UNCHANGED);
    }
}
