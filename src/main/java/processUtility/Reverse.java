package processUtility;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.IOException;

public class Reverse
{
    public Reverse() throws IOException {
    }

    public static Image reverseImage(Image image)
    {
        BufferedImage bi_scale = ImgToBufImg.toBufferedImage(image);

        int width = bi_scale.getWidth();
        int height = bi_scale.getHeight();

        BufferedImage reverseImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                int rgb = bi_scale.getRGB(i, j);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                rgb = (a << 24) | (r << 16) | (g << 8) | b;
                reverseImage.setRGB(i, j, rgb);
            }
        }

        return reverseImage;
    }
}
