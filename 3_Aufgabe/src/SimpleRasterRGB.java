import java.awt.*;
import java.util.Arrays;


// This class represents a 2D raster of RGB color entries. The class uses
// the class 'SimpleDataBufferInt' to store the entries.
public class SimpleRasterRGB implements Cloneable {

    // TODO: make these two variables 'private' and remove 'static'.
    private int width;
    private int height;
    public SimpleDataBufferInt dataBufferInt;


    // TODO: add more object variables, if needed.

    // Initialises this raster of the specified size with
    // all pixels being black, i.e. (R,G,B) = (0,0,0).
    // Preconditions: height > 0, width > 0
    public SimpleRasterRGB(int width, int height) {
        this.width = width;
        this.height = height;
        dataBufferInt = new SimpleDataBufferInt(6,width*height);
    }

    // Returns the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {
        // TODO: modify method to become an object method (not static).
        return new Color(dataBufferInt.getElem(0, y * width + x),
                dataBufferInt.getElem(1, y * width + x), dataBufferInt.getElem(2,
                y * width + x));
    }

    // Sets the color of the specified pixel.
    // Precondition: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {

        // TODO: modify method to become an object method (not static).
        dataBufferInt.setElem(0, y * width + x, color.getRed());
        dataBufferInt.setElem(1, y * width + x, color.getGreen());
        dataBufferInt.setElem(2, y * width + x, color.getBlue());
    }

    /* TODO: uncomment method definition.
    // Returns the result of convolution of 'this' with the specified filter kernel. 'this' is not
    // changed by the method call.
    // Precondition (needs to be checked):
    // filterKernel != 0 && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[0].length &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public SimpleRasterRGB convolve(double[][] filterKernel) {

        // TODO: implement method.
        return null;
    }

    // TODO: end of block to uncomment. */

    // Returns the result of convolution of the specified raster 'toBeFiltered' with the specified
    // filter kernel 'filterKernel'.
    // Precondition (needs not be checked):
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[0].length &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public static SimpleRasterRGB convolve(SimpleRasterRGB toBeFiltered, double[][] filterKernel) throws CloneNotSupportedException {

        // TODO: implement method.
        return toBeFiltered.convolve(filterKernel);
    }

    // TODO: remove following method.

    // Returns the result of convolution of the RGB raster with pixel data stored in the first
    // three rows of SimpleDataBufferInt.data with the specified filter kernel (see
    // https://de.wikipedia.org/wiki/Faltungsmatrix).
    // The method assumes that the pixel (x,y) of the raster is stored at position
    // [component][y*SimpleRasterRGB.width+x] in the data buffer array, where
    // the data bank index 'component' is 0,1 and 2 for the red, green and blue component
    // respectively.
    // The method uses 3 additional data banks with indices 3 to 5 for calculations.
    // Precondition (needs not be checked):
    // SimpleDataBufferInt.data.length >= 6 &&
    // SimpleDataBufferInt.data[i].length >= SimpleRasterRGB.width * SimpleRasterRGB.height for
    // valid i.
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[0].length &&
    // filterKernel.length < SimpleRasterRGB.width &&
    // filterKernel.length < SimpleRasterRGB.height.
    public SimpleRasterRGB convolve(double[][] filterKernel) throws CloneNotSupportedException {
        double[] temp_result;
        var copy = new SimpleRasterRGB(width,height);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < getWidth()*getHeight(); j++) {
                copy.dataBufferInt.setElem(i, j, this.dataBufferInt.getElem(i, j));
            }
        }
        //SimpleRasterRGB copy = (SimpleRasterRGB)this.clone();
        int filterSideLength = filterKernel.length / 2;

        for (int x = filterSideLength; x < width - filterSideLength; x++) {
            for (int y = filterSideLength; y < height - filterSideLength; y++) {
                //at every array position, compute filter result
                temp_result = new double[3];
                for (int xx = -filterSideLength; xx <= filterSideLength; xx++) {
                    for (int yy = -filterSideLength; yy <= filterSideLength; yy++) {
                        temp_result[0] += getPixelColor(x - xx, y - yy).getRed() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[1] += getPixelColor(x - xx, y - yy).getGreen() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                        temp_result[2] += getPixelColor(x - xx, y - yy).getBlue() * filterKernel[xx + filterSideLength][yy + filterSideLength];
                    }
                }

                copy.dataBufferInt.setElem(3, y * width + x, (int) temp_result[0]);
                copy.dataBufferInt.setElem(4, y * width + x, (int) temp_result[1]);
                copy.dataBufferInt.setElem(5, y * width + x, (int) temp_result[2]);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < width*height; j++) {
                copy.dataBufferInt.setElem(i,j, copy.dataBufferInt.getElem(i+3,j));
            }
        }
        return copy;
    }

    // Returns the width of this raster.
    public int getWidth() {

        // TODO: implement method.
        return width;
    }

    // Returns the height of this raster.
    public int getHeight() {

        // TODO: implement method.
        return height;
    }
    // Returns a mapping from all width*height pixel positions (Point) to corresponding colors
    // (Color) of the pixels. Values of color (0,0,0) are also included in the mapping.
    public TreePointColorMap asMap() {
        var map = new TreePointColorMap();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var currColor = getPixelColor(i,j);
                if(Color.BLACK!= currColor){
                    map.put(new Point(i,j), currColor);
                }
            }
        }
        return map;
        // TODO: implement method.
    }
    // Draws a line from (x1,y1) to (x2,y2) in the raster using the Bresenham algorithm.
    //Preconditions: (x1,y1) and (x2,y2) are valid coordinates of the raster, color != null
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        // TODO: modify method to become an object method (not static) operating on 'this'.
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (x1 != x2 || y1 != y2) {
            setPixelColor(x1, y1, color);

            int err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (err2 < dx) {
                err += dx;
                y1 += sy;
            }
        }

        }
    }
