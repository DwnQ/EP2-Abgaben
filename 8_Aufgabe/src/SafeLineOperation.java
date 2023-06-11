import java.awt.*;

// This class represents a line operation. More specifically, it allows to draw a line into
// a raster.
//
public class SafeLineOperation implements SafeOperationSingle // TODO: activate clause.
{
    int x1;
    int y1;
    int x2;
    int y2;
    Color color;
    public SafeLineOperation(int x1, int y1, int x2, int y2, Color colors) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = colors;
    }

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {
        if(raster == null || x1 > raster.getWidth() || x2 > raster.getWidth() || y1 > raster.getHeight() || y2 > raster.getHeight() ){
            throw new OperationException();
        }
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (x1 != x2 || y1 != y2) {
            raster.setPixelColor(x1, y1, color);

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

        raster.setPixelColor(x1, y1, color);

        return raster;
    }
    public String toString() {
        return "line " + this.x1 + " " + this.y1 + " " + this.x2 + " " + this.y2;
    }
    // TODO: define missing parts of this class.
}
