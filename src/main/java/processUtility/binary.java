package processUtility;

import processUtility.ImgToBufImg;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.IOException;

public class binary
{
    public binary() throws IOException {
    }

    public static BufferedImage binaryImage(Image image)
    {
        BufferedImage bi_scale = ImgToBufImg.toBufferedImage(image);

        int width = bi_scale.getWidth();
        int height = bi_scale.getHeight();

        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                int rgb = bi_scale.getRGB(i, j);
                binaryImage.setRGB(i, j, rgb);
            }
        }

        return binaryImage;
    }

}

