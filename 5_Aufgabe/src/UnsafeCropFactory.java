import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// A factory that creates a 'UnsafeCropOperation' object.
//
public class UnsafeCropFactory implements UnsafeFactory {

    //TODO: declare variables (if needed).

    @Override
    // Returns a new 'UnsafeCropOperation' object. The 'width' and 'height' parameters of the
    // returned object are provided by the scanner object 'sc'.
    public UnsafeOperation create(Scanner sc) {
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()){
            list.add(sc.nextInt());
        }
        if (list.size() == 2) {
            return new UnsafeCropOperation(list.get(0),list.get(1));
        }
        return null;
    }
}
