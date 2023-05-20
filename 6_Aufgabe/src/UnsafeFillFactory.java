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
        int x = sc.nextInt();
        int y = sc.nextInt();
        return new UnsafeFillOperation(x,y,this.color);
    }
}
