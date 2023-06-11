import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

// A factory that creates an 'UnsafeLineOperation' object.
//
public class UnsafeLineFactory implements UnsafeFactory {

    //TODO: declare variables.
    Color[] colors;

    // Initializes 'this' with an array 'color'.
    // 'color' contains the default color as an array entry (color[0]). An array is
    // used because it enables the default color to be changed by other classes after 'this'
    // has been initialized. Other entries in the specified array (except color[0]) are ignored.
    // Precondition: color != null && color.length > 0.
    public UnsafeLineFactory(Color[] color) {
        colors = color;
        //TODO: implement constructor.
    }

    // Returns a new 'UnsafeLineOperation' object. The coordinates for the starting point and end
    // point are provided by the scanner object 'sc'.
    @Override
    public UnsafeOperation create(Scanner sc) {
        List<Integer> list = new ArrayList<>();
        list.add(sc.nextInt());
        list.add(sc.nextInt());
        list.add(sc.nextInt());
        list.add(sc.nextInt());

        return new UnsafeLineOperation(list.get(0), list.get(1), list.get(2), list.get(3), colors[0]);
    }
}
