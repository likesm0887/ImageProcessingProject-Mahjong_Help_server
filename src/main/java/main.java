
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.client.SendObject;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import processUtility.FindFeaturePoint;
import processUtility.match;
import processUtility.threshold;

import javax.imageio.stream.FileImageOutputStream;


public class main {

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




    public static void main(String[] args) {
        ServerSocket server ;
        java.io.ObjectInputStream in ;

            try {
                server = new ServerSocket(40000);
                System.out.println("等待接收資料");
                Socket  socket = server.accept();

                in = new java.io.ObjectInputStream(socket.getInputStream());
                try {
                    SendObject data = (SendObject)in.readObject();
                    byte2image(data.imgByte,"server.bmp");
                    System.out.println(data.divnum);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}

