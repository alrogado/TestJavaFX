package mat;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 * Created by alvaro.lopez on 24/07/2017.
 */
public class TestImgFromVideoCameraShow {
    public static void main(String[] args) {
        loadNativeDll();

        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		/*
		 * Declare a New Frame Here -------------------------
		 */
        ImgShow im = new ImgShow("Video Preview");
        // This create a Window with Title Video Preview and is autoSized to the image it contains
        // or
        // new ImgShow("Title",int Height,int Width); to set custom height and width 
		
		  
		 
		/* You can even Customise the ImShow Frame or Window 
		 * The image is loaded onto a JFrame which is a public member 
		 * so that anyone could customise it
		 * ImgShow.Window is the JFrame that one could customise
		 * For example : 
		 * By default the Window is not Resizable so to make it resizable:
		 */
        im.Window.setResizable(true);
        // -------------------------
        Mat m = new Mat();
        VideoCapture vcam = new VideoCapture(0);

        // loop until VideoCamera is Available
        while (vcam.isOpened() == false)
            ;

        // Bug Fix: Loop until initial image frames are empty
        while (m.empty()) {
            vcam.retrieve(m);

        }

        while (true) {

            vcam.retrieve(m);
            /***
             * Show the image
             */
            //System.out.println(m.dump());
            im.showImage(m);
            /************/
        }
    }

    public static void loadNativeDll() {
        String osName = System.getProperty("os.name");
        String opencvpath = System.getProperty("user.dir")+"\\src\\test\\resources";
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
    }

}
