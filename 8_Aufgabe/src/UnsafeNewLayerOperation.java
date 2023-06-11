import codedraw.CodeDraw;

import java.awt.*;
import java.util.Scanner;

// This class represents an operation creating a new top-most layer.
//
public class UnsafeNewLayerOperation implements UnsafeOperation {
    private Scanner sc;

    public UnsafeNewLayerOperation(Scanner sc) {
        this.sc = sc;
    }

    // TODO: specification of the method.
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) {
        raster = new MultiLayerRasterRGBA(new TreeSparseRasterRGBA(raster.getWidth(),raster.getHeight()), raster);
        return raster;
    }
}
