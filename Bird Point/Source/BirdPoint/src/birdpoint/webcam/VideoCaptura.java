/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package birdpoint.webcam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Danilo Filitto
 */
public class VideoCaptura {

    VideoCapture video;
    Mat imageMat = new Mat();
    BufferedImage imageBuffer;
    byte[] dat; 
    
    public VideoCaptura(){
        this.video = new VideoCapture();
        this.video.open(0);
    }
    
    public BufferedImage capturaQuadroBufferedImage(){
        this.video.read(this.imageMat); //Captura o quadro
        imageBuffer = this.matToBufferedImage(this.imageMat); //Converte para imageBuffer  
        return imageBuffer;
    }
    
    public Mat capturaQuadroMat(){
        this.video.read(this.imageMat); //Captura o quadro
        return imageMat;
    }
 
 
     
    public BufferedImage matToBufferedImage(Mat matrix) {  
     int cols = matrix.cols();  
     int rows = matrix.rows();  
     int elemSize = (int)matrix.elemSize();  
     byte[] data = new byte[cols * rows * elemSize];  
     int type;  
     matrix.get(0, 0, data);  
     switch (matrix.channels()) {  
       case 1:  
         type = BufferedImage.TYPE_BYTE_GRAY;  
         break;  
       case 3:  
         type = BufferedImage.TYPE_3BYTE_BGR;  
         // bgr to rgb  
         byte b;  
         for(int i=0; i<data.length; i=i+3) {  
           b = data[i];  
           data[i] = data[i+2];  
           data[i+2] = b;  
         }  
         break;  
       default:  
         return null;  
     }  
     BufferedImage image = new BufferedImage(cols, rows, type);  
     image.getRaster().setDataElements(0, 0, cols, rows, data);  
     return image;  
   }  
    
    static{
        loadLibraries();
    }
    
    private static void loadLibraries() {

    try {
        InputStream in = null;
        File fileOut = null;
        String osName = System.getProperty("os.name");
        String opencvpath = System.getProperty("user.dir");
        if(osName.startsWith("Windows")) {
            int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
            if(bitness == 32) {
                opencvpath=opencvpath+"\\opencv\\x86\\";
            }
            else if (bitness == 64) { 
                opencvpath=opencvpath+"\\opencv\\x64\\";
            } else { 
                opencvpath=opencvpath+"\\opencv\\x86\\"; 
            }           
        } 
        else if(osName.equals("Mac OS X")){
            opencvpath = opencvpath+"Your path to .dylib";
        }
        System.out.println(opencvpath);
        System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
    } catch (Exception e) {
        throw new RuntimeException("Failed to load opencv native library", e);
    }
}
    
   
    
}
