import java.awt.*;
import java.util.Arrays;
import java.util.List;

// This class represents a flood fill operation. More specifically, it allows to set the area of
// contiguous pixels of the same color to a specified color, starting from an initial position and
// using 4-neighborhood.
//
public class UnsafeFillOperation implements UnsafeOperation {
    private Color[] colors;
    private List<Integer> values;
    public UnsafeFillOperation(Color[] color, List<Integer> list) {
        this.colors = color;
        values = list;
    }

    // TODO: define missing parts of this class.

    // TODO: add method specification.
    public RasterizedRGB execute(RasterizedRGB raster) {
        SimplePointQueue queue = new SimplePointQueue(10);
        queue.add(new Point(values.get(0),values.get(1)));
        var currentColor = raster.getPixelColor(values.get(0),values.get(1));

        while (queue.size()>0){
            Point cord = queue.poll();
            if(cord == null){
                continue;
            }

            var prevColor = raster.getPixelColor(cord.getX(), cord.getY());
            raster.setPixelColor(cord.getX(), cord.getY(), colors[0]);

            if(prevColor == null || prevColor.equals(currentColor)){
                queue.add(new Point(cord.getX(),cord.getY()+1));
                queue.add(new Point(cord.getX(),cord.getY()-1));
                queue.add(new Point(cord.getX()+1,cord.getY()));
                queue.add(new Point(cord.getX()-1,cord.getY()));
            }


            raster.setPixelColor(cord.getX(), cord.getY(), colors[0]);
        }
        return raster;
    }
}
