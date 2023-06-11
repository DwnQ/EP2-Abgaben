import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class SafeLoadSequenceFactory implements SafeFactory {
    private Map<String, SafeFactory> commandMap;

    // Initializes 'this' with a mapping that associates the string of the command
    // with the corresponding factory.
    // Precondition: commandMap != null.
    public SafeLoadSequenceFactory(Map<String, SafeFactory> commandMap) {

        this.commandMap = commandMap;
    }

    @Override
    // Returns a new 'SafeOperation' from commands stored in a file. The path to the file is
    // provided by the next token of 'sc' (other tokens are ignored).

    // The first word of every line of the file represents a command and the rest of the line
    // provides the parameters required by the corresponding 'SafeFactory' object. All tokens of
    // the line are separated by a blank.

    // The first line of the file has to be a 'create' command.

    // If the file contains exactly one valid line, the method creates a 'SafeOperationSingle'
    // object.

    // If the file contains more than one valid line, the method creates an object of
    // 'SafeOperationSequence'.
    // If the filename is missing or the file can not be found, the file is not in the required
    // format (including the case where the first command is not 'create') or a command is unknown,
    // the method throws a 'FactoryException' using a message string with the information about
    // the cause of the exception.
    public SafeOperation create(Scanner sc) throws FactoryException {

        if (!sc.hasNext()) throw new FactoryException("Not enough Values given!");
        String path = sc.next();
        SafeOperation operations;

        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String line = scanner.nextLine();
            String[] splits = line.split(" ");

            if (!splits[0].equals("create")) throw new IllegalArgumentException("First element is not a create command");
            operations = new SafeCreateOperation(Integer.parseInt(splits[1]), Integer.parseInt(splits[2]));

            int[] canvasSize = new int[]{Integer.parseInt(splits[1]), Integer.parseInt(splits[2])};


            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                splits = line.split(" ");

                for (int i = 1; i < splits.length; i++) {
                    if (Integer.parseInt(splits[i]) >= canvasSize[0] || Integer.parseInt(splits[i]) >= canvasSize[1])
                        throw new FactoryException("outside of canvas");
                }

                operations = new SafeOperationSequence(operations,
                        commandMap.get(splits[0]).create(new Scanner(line.replace(splits[0] + " ", ""))));
            }
            scanner.close();
        } catch (Exception e) {
            throw new FactoryException(e.getMessage());
        }
        return operations;
    }
}
