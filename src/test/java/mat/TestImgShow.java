package mat;

import com.google.common.io.Files;
import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLDouble;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static mat.TestImgFromVideoCameraShow.loadNativeDll;

/**
 * Created by alvaro.lopez on 24/07/2017.
 */
public class TestImgShow {

    public static void main(String[] args) throws IOException {
        loadNativeDll();
        TestImgShow testImgShow = new TestImgShow();
        testImgShow.process("C:\\Users\\alvaro.lopez\\java\\projects\\TestJavaFX\\src\\test\\resources\\opencv\\mat\\MeshMaterial.mat");
    }

    public ProcessedImage process(String originalPath) {
        ImageMetadata imageMetadata = new ImageMetadata();
        imageMetadata.setOriginalPath(originalPath);
        File originalFile = new File(imageMetadata.getOriginalPath());

        Mat originalImage = Highgui.imread(originalFile.getAbsolutePath());
        //Mat thumbnailImage = resize(originalImage, 200);

        File thumbnailFile = getThumbnailFile(originalFile);
        //Highgui.imwrite(thumbnailFile.getAbsolutePath(), thumbnailImage);
        imageMetadata.setThumbnailPath(thumbnailFile.getAbsolutePath());

        ProcessedImage processedImage = new ProcessedImage(imageMetadata);
        processedImage.setHistogram(histogram(originalImage));

        return processedImage;
    }

    Mat resize(Mat originalImage, int maxDimension) {
        Size originalSize = originalImage.size();
        Size newSize = new Size();
        if (originalSize.height > originalSize.width) {
            newSize.height = maxDimension;
            newSize.width = originalSize.width * maxDimension / originalSize.height;
        } else {
            newSize.height = originalSize.height * maxDimension / originalSize.width;
            newSize.width = maxDimension;
        }

        Mat resizedImage = new Mat();
        Imgproc.resize(originalImage, resizedImage, newSize);
        return resizedImage;
    }



    public static File getThumbnailFile(File originalFile) {
        return getProcessedFile(originalFile, "thumbnail");
    }

    private static File getProcessedFile(File original, String modifier) {
        String originalDir = original.getParent();
        File picturepoDir = new File(originalDir, ".picturepo");
        File modifierDir = new File(picturepoDir, modifier);
        String name = Files.getNameWithoutExtension(original.getName());
        String extension = Files.getFileExtension(original.getName());
        String processedName = name + "_" + modifier + "." + extension;
        return new File(modifierDir, processedName);
    }

    Mat histogram(Mat img) {
        Mat hist = new Mat();
        MatOfInt channels = new MatOfInt(0, 1);
        Mat mask = new Mat();
        MatOfInt histSize = new MatOfInt(50, 60);
        MatOfFloat ranges = new MatOfFloat(0, 180, 0, 256);
        Imgproc.calcHist(Arrays.asList(img), channels, mask, hist, histSize, ranges);
        return hist;
    }

    class ProcessedImage {

        private ImageMetadata imageMetadata;
        private Mat histogram;

        public ProcessedImage(ImageMetadata imageMetadata) {
            this.imageMetadata = imageMetadata;
        }

        public ImageMetadata getImageMetadata() {
            return imageMetadata;
        }

        public Mat getHistogram() {
            return histogram;
        }

        public void setHistogram(Mat histogram) {
            this.histogram = histogram;
        }
    }

    class ImageMetadata {

        private String name;

        private String originalPath;
        private String thumbnailPath;

        private String similarityGroup;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSimilarityGroup() {
            return similarityGroup;
        }

        public void setSimilarityGroup(String similarityGroup) {
            this.similarityGroup = similarityGroup;
        }

        public String getOriginalPath() {
            return originalPath;
        }

        public void setOriginalPath(String originalPath) {
            this.originalPath = originalPath;
        }

        public String getThumbnailPath() {
            return thumbnailPath;
        }

        public void setThumbnailPath(String thumbnailPath) {
            this.thumbnailPath = thumbnailPath;
        }
    }

}
