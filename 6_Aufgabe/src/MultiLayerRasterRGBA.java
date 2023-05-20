import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Represents a raster consisting of at least two layers.
//
public class MultiLayerRasterRGBA implements Layered //TODO: activate clause.
{
    // TODO: define missing parts of this class.
    private Layered head;
    private SingleLayer foreground;

    public MultiLayerRasterRGBA(Layered layered, TreeSparseRasterRGBA newRaster) {

        head = layered;
        foreground = newRaster;
    }

    @Override
    public Layered getBackground() {
        return head;
    }

    @Override
    public int getWidth() {
        return foreground.getWidth();
    }

    @Override
    public int getHeight() {
        return foreground.getHeight();
    }

    @Override
    public Color getPixelColor(int x, int y) {
        return RasterRGBA.over(foreground.getPixelColor(x, y), head.getPixelColor(x, y));
    }

    @Override
    public void setPixelColor(int x, int y, Color color) {
        foreground.setPixelColor(x,y,color);
    }

    @Override
    public void convolve(double[][] filterKernel) {
        foreground.convolve(filterKernel);
    }

    @Override
    public void crop(int width, int height) {
        foreground.crop(width,height);
    }

    @Override
    public RasterizedRGBIterator iterator() {
        var a = new LayerIterator(this);
        return a;
    }

    @Override
    public Layered newLayer() {
        return new MultiLayerRasterRGBA(this, new TreeSparseRasterRGBA(getWidth(), getHeight()));
    }

    @Override
    public int numberOfLayers() {
        if (head != null) return 1 + head.numberOfLayers();
        return 0;
    }

    @Override
    public SingleLayer getForeground() {
        return foreground;
    }

    // Initializes 'this' with top-layer 'foreground' and 'background'.
    // Performs dynamic type checking of 'background'. If 'background' is an instance of 'Layered'
    // this constructor initializes 'this' with top-layer 'foreground' and layers of the
    // 'background'.
    // If 'background' is not an instance of 'Layered', 'background' is copied to a new object of
    // 'SingleLayer' which is then used to initialize the background.
    // Width and height of this raster is determined by width and height of the 'foreground'
    // raster.
    // Pixels that are not defined in the 'background' are assumed to have color (0,0,0,0).
    public class LayerIterator implements RasterizedRGBIterator {
        private SingleLayer data;
        private Layered next;
        public LayerIterator(Layered layered){
            this.data = layered.getForeground();
            this.next = layered.getBackground();
        }

        @Override
        public RasterizedRGB next() {
            if (!hasNext()) return null;

            SingleLayer toReturn = data;

            data = next == null ? null : next.getForeground();
            next = next instanceof SingleLayer || next == null ? null : next.getBackground();

            return toReturn;
        }
        @Override
        public boolean hasNext() {
            return this.data != null;
        }
    }
}

