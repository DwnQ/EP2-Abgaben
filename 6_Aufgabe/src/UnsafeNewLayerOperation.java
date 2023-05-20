import java.util.Scanner;

// This class represents an operation creating a new top-most layer.
//
public class UnsafeNewLayerOperation implements UnsafeOperation {
    Scanner sc;

    public UnsafeNewLayerOperation(Scanner sc) {
        this.sc = sc;
    }

    // TODO: specification of the method.
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) {
        if(raster instanceof MultiLayerRasterRGBA){
            return new MultiLayerRasterRGBA((MultiLayerRasterRGBA)raster, new TreeSparseRasterRGBA(raster.getWidth(),raster.getHeight()));
        }
        return new TreeSparseRasterRGBA(raster.getWidth(),raster.getHeight());
    }
}
