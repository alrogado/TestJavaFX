package moleculesampleapp;

import java.io.File;
import org.bytedeco.javacpp.indexer.UByteBufferIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

/**
 *
 * @author BTACTC
 */
public class CupWrapping {

    Mat image;
    Mat dstImage;

    int width;
    int height;

    public CupWrapping(File imageFile) {

        image = imread(imageFile.getAbsolutePath(), CV_LOAD_IMAGE_COLOR);

        width = image.size().width();
        height = image.size().height();

        dstImage = new Mat(width, height, image.type());

        UByteBufferIndexer sI = image.createIndexer();
        UByteBufferIndexer sD = dstImage.createIndexer();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point2f current_pos = new Point2f(x, y);
                current_pos = convert_pt(current_pos, width, height);
                Point top_left = new Point((int) current_pos.x(), (int) current_pos.y()); //top left because of integer rounding

                //make sure the point is actually inside the original image
                if (top_left.x() < 0
                        || top_left.x() > width - 2
                        || top_left.y() < 0
                        || top_left.y() > height - 2) {
                    continue;
                }

                //bilinear interpolation
                float dx = current_pos.x() - top_left.x();
                float dy = current_pos.y() - top_left.y();

                float weight_tl = (float) ((1.0 - dx) * (1.0 - dy));
                float weight_tr = (float) ((dx) * (1.0 - dy));
                float weight_bl = (float) ((1.0 - dx) * (dy));
                float weight_br = (dx) * (dy);

                byte value = (byte) (weight_tl * sI.get(top_left.y(), top_left.x())
                        + weight_tr * sI.get(top_left.y(), top_left.x() + 1)
                        + weight_bl * sI.get(top_left.y() + 1, top_left.x())
                        + weight_br * sI.get(top_left.y() + 1, top_left.x() + 1));
                sD.put(y, x,value);
            }
        }

        imshow("", dstImage);

        waitKey(0);

    }

    public Point2f convert_pt(Point2f point, int w, int h) {
        //center the point at 0,0
        Point2f pc = new Point2f(point.x() - w / 2, point.y() - h / 2);

        //these are your free parameters
        float f = w;
        float r = w;

        float omega = w / 2;
        float z0 = (float) (f - Math.sqrt(r * r - omega * omega));

        float zc = (float) ((2 * z0 - Math.sqrt(4 * z0 * z0 - 4 * (pc.x() * pc.x() / (f * f) + 1) * (z0 * z0 - r * r))) / (2 * (pc.x() * pc.x() / (f * f) + 1)));
        Point2f final_point = new Point2f(pc.x() * zc / f, pc.y() * zc / f);
        /*final_point.x() = final_point.x() +  w / 2;
        final_point.y() += h / 2;*/
        return final_point;

    }

    public static void main(String[] args) {

        File imageFile = new File("image/C13.jpg");
        CupWrapping wrap = new CupWrapping(imageFile);
    }

}