import java.awt.*;

// This class represents a sparse 2D raster of RGB color entries.
//
// This class is efficient for representing images where only a small fraction of pixels is
// non-empty, meaning they have a color different from (0,0,0) corresponding to Color.BLACK.
// The class uses internally an object of 'HashPointColorMap' to associate each non-empty
// pixel position (x,y) to the corresponding color. Only pixels with non-zero values are stored.
// Positions that are not in the set of keys of the map are considered to have value (0,0,0).
//
public class HashSparseRasterRGB implements RasterizedRGB {

    private int width;
    private int height;
    HashPointColorMap map;

    // Initialises this raster of the specified size as an empty
    // raster (all pixels being black, i.e. (R,G,B) = (0,0,0)).
    // Preconditions: height > 0, width > 0
    public HashSparseRasterRGB(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new HashPointColorMap();
    }

    // Returns the color of the specified pixel.
    // Preconditions: (x,y) is a valid coordinate of the raster
    public Color getPixelColor(int x, int y) {
        Color c = map.get(new Point(x, y));
        return c == null ? Color.BLACK : c;
    }

    // Sets the color of the specified pixel. (If 'color' is 'Color.BLACK', the method
    // ensures that the pixel is not contained in the internal map.)
    // Preconditions: (x,y) is a valid coordinate of the raster, color != null
    public void setPixelColor(int x, int y, Color color) {
        if (color.equals(Color.BLACK)) return;
        map.put(new Point(x, y), color);
    }

    // Returns the width of this raster.
    public int getWidth() {
        return width;
    }

    // Returns the height of this raster.
    public int getHeight() {
        return height;
    }

    // Performs the convolution of 'this' with the specified filter kernel. 'this' is the result of
    // the operation.
    // The implementation of this method exploits the sparse structure of the raster by
    // calculating the convolution only at non-empty pixel positions.
    // Preconditions:
    // filterKernel != null && filterKernel.length > 0 &&
    // filterKernel.length % 2 == 1 &&
    // filterKernel.length == filterKernel[i].length (for valid i) &&
    // filterKernel.length < this.getWidth() &&
    // filterKernel.length < this.getHeight().
    public void convolve(double[][] filterKernel) {
        double[] temp_result;
        HashPointColorMap ret = new HashPointColorMap();
        SimplePointQueue queue = map.keys();
        int kernelLength = filterKernel.length;
        int filterSideLength = kernelLength / 2;

        while (queue.size() > 0) {
            Point p = queue.poll();
            if(p == null){
                continue;
            }
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
                    ret.put(new Point(x + kx, y + ky), tempColor);
                }
            }
        }
        map = ret;
    }

    // Crops 'this' to the rectangular region with upper left coordinates (0,0)
    // and lower right coordinates (width-1, height-1).
    // Precondition: width <= this.getWidth() && height <= this.getHeight().
    public void crop(int width, int height) {
        this.width = width;
        this.height = height;

        SimplePointQueue q = this.map.keys();
        if (q.size() < 1) return;

        while (q.size() > 0) {
            Point p = q.poll();
            if(p == null) continue;

            if (p.getX() > width || p.getY() > height) map.remove(p);
        }
    }
}
