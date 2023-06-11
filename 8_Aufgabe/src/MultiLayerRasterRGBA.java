import java.awt.*;
import java.util.*;

// Represents a raster consisting of at least two layers.
//
public class MultiLayerRasterRGBA implements Layered //TODO: activate clause.
{
    // TODO: define missing parts of this class.
    private Layered head;
    private SingleLayer foreground;
    // Initializes 'this' with top-layer 'foreground' and 'background'.
    // Performs dynamic type checking of 'background'. If 'background' is an instance of 'Layered'
    // this constructor initializes 'this' with top-layer 'foreground' and layers of the
    // 'background'.
    // If 'background' is not an instance of 'Layered', 'background' is copied to a new object of
    // 'SingleLayer' which is then used to initialize the background.
    // Width and height of this raster is determined by width and height of the 'foreground'
    // raster.
    // Pixels that are not defined in the 'background' are assumed to have color (0,0,0,0).
    public MultiLayerRasterRGBA(SingleLayer foreground, RasterizedRGB background) {
        if(background instanceof Layered){
            this.foreground = foreground;
            head = (Layered)background;
        }else{
            SingleLayer copy = new TreeSparseRasterRGBA(background.getWidth(), background.getHeight());
            background.copyTo(copy);
            System.out.println("a");
            this.foreground = copy;
        }
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
        head.crop(width,height);
        foreground.crop(width,height);
    }

    @Override
    public RasterizedRGBIterator iterator() {
        return new LayerIterator(this);
    }

    @Override
    public Layered newLayer() {
        return new MultiLayerRasterRGBA(new TreeSparseRasterRGBA(getWidth(), getHeight()),this);
    }

    @Override
    public int numberOfLayers() {
        return 1 + head.numberOfLayers();
    }

    @Override
    public SingleLayer getForeground() {
        return foreground;
    }
    // Returns an iterator that iterates over the layers of this raster in a bottom-to-top order.
// (The first iteration returns the bottom-most layer.)
    public RasterizedRGBIterator reversedIterator() {

        var temp = this.asList();
        if(temp.size() < 1){
            throw new IndexOutOfBoundsException("BYE-BYE");
        }
        MultiLayerRasterRGBA out = new MultiLayerRasterRGBA((SingleLayer) temp.get(1), temp.get(0));
        for (int i = 2; i < numberOfLayers(); i++) {
            out = new MultiLayerRasterRGBA((SingleLayer) temp.get(i), out);
        }
        return new LayerIterator(out);
    }

    // Returns a new list with all the layers of this raster in top-to-bottom order. The size of the
// list equals the value returned by 'numberOfLayers()'.
    public java.util.ArrayList<RasterizedRGB> asList() {
        var list = new ArrayList<RasterizedRGB>();
        var temp = iterator();

        while (temp.hasNext()){
            list.add(temp.next());
        }
        return list;
    }
    @Override
    public String toString() {
        var it = iterator();
        String out = "";
        int counter = 1;
        while (it.hasNext()){
            out += counter + ". " + it.next() + "\n";
            counter++;
        }
        return out;
    }
}

