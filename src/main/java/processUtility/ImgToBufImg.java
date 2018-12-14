package processUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgToBufImg
{

    public ImgToBufImg(Image image)
    {
    }

    public static BufferedImage toBufferedImage(Image image)       //Image 轉 BufferedImage
    {
        if (image instanceof BufferedImage)
        {
            return (BufferedImage)image;
        }

        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try
        {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        }
        catch (HeadlessException e) { }

        if (bimage == null)
        {
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }
}

