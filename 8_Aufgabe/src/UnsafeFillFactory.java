import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// A factory that creates a 'UnsafeFillOperation' object.
//
public class UnsafeFillFactory implements UnsafeFactory {

    // TODO: define missing parts of this class.
    private Color color[];

    // TODO: add constructor specification.
    public UnsafeFillFactory(Color[] c) {

        // TODO: implement constructor.
        this.color = c;
    }

    // TODO: add method specification.
    // Returns a new 'UnsafeCropOperation' object. The 'x' and 'y' parameters of the
    // returned object are provided by the scanner object 'sc'.
    public UnsafeFillOperation create(Scanner sc) {

        // TODO: implement method.
        return new UnsafeFillOperation(sc.nextInt(), sc.nextInt(), color);
    }
}
