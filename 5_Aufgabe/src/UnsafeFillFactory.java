import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// A factory that creates a 'UnsafeFillOperation' object.
//
public class UnsafeFillFactory implements UnsafeFactory {

    // TODO: define missing parts of this class.
    Color[] color;
    // TODO: add constructor specification.
    public UnsafeFillFactory(Color[] c) {
        this.color = c;
        // TODO: implement constructor.
    }

    // TODO: add method specification.
    public UnsafeFillOperation create(Scanner sc) {
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()){
            list.add(sc.nextInt());
        }
        if (list.size() == 2) {
            return new UnsafeFillOperation(this.color, list);

        }
        return null;
        // TODO: implement method.
    }
}
