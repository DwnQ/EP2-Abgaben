import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SafeSaveFactory implements SafeFactory {

    private final ArrayList<String> data;

    public SafeSaveFactory(ArrayList<String> data) {
        this.data = data;
    }


    @Override
    public SafeOperation create(Scanner sc) throws FactoryException {
        String path = sc.next();

        String temp = Arrays.toString(data.toArray()).replace("[", "").replace("]", "").replace(", ", "\n");
        return new SafeSaveOperation(path, temp);
    }
}
