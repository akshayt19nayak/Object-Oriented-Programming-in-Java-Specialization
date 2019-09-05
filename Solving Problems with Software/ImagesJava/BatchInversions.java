
/**
 * Write a description of Class2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage){
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //set pixel's red to average
            pixel.setRed(255 - inPixel.getRed());
            //set pixel's green to average
            pixel.setGreen(255 - inPixel.getGreen());
            //set pixel's blue to average
            pixel.setBlue(255 - inPixel.getBlue());
        }
        //outImage is your answer
        return outImage;
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            String fileName = inImage.getFileName();
            String newName = "inverted-" + fileName;
            ImageResource outImage = makeInversion(inImage);
            outImage.setFileName(newName);
            outImage.save();
        }
    }
}
