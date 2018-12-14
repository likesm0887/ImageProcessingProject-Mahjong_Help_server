
import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.client.SendObject;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import processUtility.Cutup;
import processUtility.FindFeaturePoint;
import processUtility.match;
import sun.applet.Main;

import javax.imageio.stream.FileImageOutputStream;


public class main
{

    private static ArrayList<Mat> imageMat = new ArrayList<>();
    public static void byte2image(byte[] data, String path){
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " +  path);
        } catch(Exception ex) {
            System.out.println("Exception: "+ ex);
            ex.printStackTrace();
        }
    }

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void matListToImage( ArrayList<Mat> imageMat)
    {
        for (int i =0;i<imageMat.size();i++)
        {
            Highgui.imwrite("MatImage" + i + ".jpg", imageMat.get(i));
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket server ;
        java.io.ObjectInputStream in ;
        Cutup cutup =new Cutup();
            try {

                server = new ServerSocket(40000);
                System.out.println("等待接收資料");
                Socket  socket = server.accept();

                in = new java.io.ObjectInputStream(socket.getInputStream());
                try {
                    SendObject data = (SendObject)in.readObject();
                    //byte2image(data.imgByte,"server.bmp");
                    Image image = Toolkit.getDefaultToolkit().getImage("server.bmp");
                    imageMat = cutup.cutImage(image,1,Integer.parseInt(data.divnum));
                    matListToImage(imageMat);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}

