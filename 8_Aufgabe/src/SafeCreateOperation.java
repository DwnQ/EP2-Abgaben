import java.awt.*;

public class SafeCreateOperation implements SafeOperationSingle // TODO: activate clause.
{
    private int x;
    private int y;
    public SafeCreateOperation(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {
        try {

            return new TreeSparseRasterRGBA(x, y);
        }catch (Exception e){
            throw new OperationException();
        }
    }
    @Override
    public String toString() {
        return "create " + this.x + " " + this.y;
    }
}
