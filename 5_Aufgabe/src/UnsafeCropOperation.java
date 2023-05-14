// This class represents a crop operation.
//
public class UnsafeCropOperation implements UnsafeOperation {

    //TODO: declare variables.

    // Initializes this operation with width and height of
    // the crop region. The crop region is bounded by the rectangular region with the upper left
    // corner [0,0] and the lower left corner [width-1, height-1].
    // Precondition: width >= 0 && height >= 0.
    private int width;
    private int height;
    public UnsafeCropOperation(int width, int height) {
        this.width = width;
        this.height = height;
        // TODO: implement constructor.
    }

    // Returns the height of the crop region.
    public int getHeight() {

        // TODO: implement method.
        return height;
    }

    // Returns the width of the crop region.
    public int getWidth() {

        // TODO: implement method.
        return width;
    }

    @Override
    // Executes the crop operation on the specified 'raster'.
    // The specified object is directly modified by this method call.
    // The returned raster is identical to the specified 'raster'.
    // Precondition:
    // this.getWidth() <= raster.getWidth() && this.getHeight() <= raster.getHeight().
    public RasterizedRGB execute(RasterizedRGB raster) {
        raster.crop(width,height);
        // TODO: implement method.
        return raster;
    }
}
