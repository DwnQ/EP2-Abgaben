import jdk.swing.interop.SwingInterOpUtils;

import java.awt.*;

// This class represents a sparse 2D raster of RGB color entries. It has the same functionality
// as the class 'SimpleRasterRGB', but its implementation differs. It additionally has a
// flood-fill method.
//
// This class is efficient for representing images where only a small fraction of pixels is
// non-empty, meaning they have a color different from (0,0,0) corresponding to Color.BLACK.
// The class uses internally an object of 'SimplePointColorMap' to associate each non-empty
// pixel position (x,y) to the corresponding color. Only pixels with non-zero values are stored.
// Positions that are not in the set of keys of the map are considered to have value (0,0,0).
//
public class SimpleSparseRasterRGB {

    private int width;
    private int height;
    private SimplePointColorMap map;

    // Initialises this raster of the specified size as an empty
    // raster (all pixels being black, i.e. (R,G,B) = (0,0,0)).
    // Preconditions: height > 0, width > 0
    public SimpleSparseRasterRGB(int width, int height) {
        this.height= height;
        this.width = width;
        map = new SimplePointColorMap(width*height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map.put(new Point(i,j), Color.BLACK);
            }
        }
        // TODO: implement constructor.
    }

    // Returns the color of the specified pixel.
    // Preconditions: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {
        var res = map.get(new Point(x,y));
        if(res == null ){
            return Color.BLACK;
        }
        return res;
    }

    // Sets the color of the specified pixel. (If 'color' is 'Color.BLACK', the method
    // ensures that the pixel is not contained in the internal map.)
    // Preconditions: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {
        if (color != Color.BLACK) {
            map.put(new Point(x, y), color);
        } else {
            map.remove(new Point(x, y));
        }
    }

    // Returns the result of convolution of 'this' with the specified filter kernel. 'this' is not
    // changed by the method call.
    // The implementation of this method exploits the sparse structure of the raster by
    // calculating the convolution only at non-empty pixel positions.
    // Preconditions:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i) &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public SimpleSparseRasterRGB convolve(double[][] filterKernel) {

        // TODO: implement method.

        double[] temp_result;
        SimplePointQueue queue = map.keys();
        SimpleSparseRasterRGB tempRaster = new SimpleSparseRasterRGB(this.width, this.height);
        int kernelLength = filterKernel.length;
        int filterSideLength = kernelLength / 2;

        while (queue.peek() != null) {
            Point p = queue.poll();
            int x = p.getX();
            int y = p.getY();
            for (int kx = -filterSideLength; kx <= filterSideLength; kx++) {
                for (int ky = -filterSideLength; ky <= filterSideLength; ky++) {
                    temp_result = new double[3];
                    for (int xx = -1; xx < kernelLength - 1; xx++) {
                        for (int yy = -1; yy < kernelLength - 1; yy++) {
                            temp_result[0] += this.getPixelColor(x + xx + kx, y + yy + ky).getRed() * filterKernel[xx + 1][yy + 1];
                            temp_result[1] += this.getPixelColor(x + xx + kx, y + yy + ky).getGreen() * filterKernel[xx + 1][yy + 1];
                            temp_result[2] += this.getPixelColor(x + xx + kx, y + yy + ky).getBlue() * filterKernel[xx + 1][yy + 1];
                        }
                    }
                    Color tempColor = new Color((int) temp_result[0], (int) temp_result[1], (int) temp_result[2]);
                    tempRaster.setPixelColor(x + kx, y + ky, tempColor);
                }
            }
        }

        return tempRaster;
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

    // Sets the area of contiguous pixels of the same color to the specified color 'color', starting from
    // the initial pixel with position (x,y) and using 4-neighborhood. The method is not
    // recursive, instead it internally uses an object of 'SimplePointQueue' to which unprocessed
    // neighboring positions of the current position are added (the queue stores positions
    // that are still waiting to be processed).
    // 'floodFill' does nothing if the pixel at position (x,y) already has color 'color'.
    // Preconditions: (x,y) are valid coordinates of the raster, color != null.
    public void floodFill(int x, int y, Color color) {
        SimplePointQueue queue = new SimplePointQueue(10);
        queue.add(new Point(x,y));
        var currentColor = getPixelColor(x,y);

        while (queue.size()>0){
            Point cord = queue.poll();
            if(cord == null){
                continue;
            }

            var prevColor = map.put(cord,color);
            if(prevColor.equals(currentColor)){
                queue.add(new Point(cord.getX(),cord.getY()+1));
                queue.add(new Point(cord.getX(),cord.getY()-1));
                queue.add(new Point(cord.getX()+1,cord.getY()));
                queue.add(new Point(cord.getX()-1,cord.getY()));
            }
            if(!prevColor.equals(currentColor) && !prevColor.equals(color)){
                map.put(cord,prevColor);
            }
        }
    }

    // Draws a line from (x1,y1) to (x2,y2) in the raster using the Bresenham algorithm.
    // Preconditions:
    // (x1,y1) and (x2,y2) are valid coordinates of the raster, color != null.
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {


        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (x1 != x2 || y1 != y2) {
            this.map.put(new Point(x1, y1), color);

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
        this.map.put(new Point(x1, y1), color);
    }
}
