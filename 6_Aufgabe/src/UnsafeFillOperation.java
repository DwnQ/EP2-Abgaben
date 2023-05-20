import java.awt.*;

// This class represents a flood fill operation. More specifically, it allows to set the area of
// contiguous pixels of the same color to a specified color, starting from an initial position and
// using 4-neighborhood.
//
public class UnsafeFillOperation implements UnsafeOperation {

    private int x;
    private int y;
    private Color[] color;

    public UnsafeFillOperation(int x, int y, Color [] color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public RasterizedRGB execute(RasterizedRGB raster) {
        SimplePointQueue queue = new SimplePointQueue(10);
        queue.add(new Point(x,y));
        var currentColor = raster.getPixelColor(x,y);

        while (queue.size()>0){
            Point cord = queue.poll();

            var prevColor = raster.getPixelColor(cord.getX(),cord.getY());
            raster.setPixelColor(cord.getX(),cord.getY(),color[0]);

            if(prevColor == null || prevColor.equals(currentColor)){
                queue.add(new Point(cord.getX(),cord.getY()+1));
                queue.add(new Point(cord.getX(),cord.getY()-1));
                queue.add(new Point(cord.getX()+1,cord.getY()));
                queue.add(new Point(cord.getX()-1,cord.getY()));
            }

            if(!prevColor.equals(currentColor) && !prevColor.equals(color)){
                raster.setPixelColor(cord.getX(),cord.getY(),prevColor);
            }
        }

        return raster;
    }
}
