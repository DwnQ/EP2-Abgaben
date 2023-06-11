import java.awt.*;
import java.util.Scanner;

// A factory that creates an 'SafeLineOperation' object.
//
public class SafeLineFactory implements SafeFactory // TODO: activate clause.
{
    Color[] colors;
    public SafeLineFactory(Color[] colors) {
        this.colors = colors;
    }

    @Override
    public SafeOperation create(Scanner sc) throws FactoryException {

        int [] values = new int[4];
        int i = 0;
        while(sc.hasNext() && i < values.length){
            values[i++] = sc.nextInt();
        }
        if(sc.hasNextInt()){
            throw new FactoryException();
        }
        if(values[0] < 0 || values[1] < 0 || values[2] < 0 || values[3] < 0){
            throw new FactoryException();
        }

        return new SafeLineOperation(values[0] , values[1] , values[2] , values[3],colors[0]);
    }

    // TODO: define missing parts of this class.
}
