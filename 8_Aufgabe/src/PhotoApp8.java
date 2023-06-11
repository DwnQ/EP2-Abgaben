import codedraw.CodeDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PhotoApp8 {

    public static void main(String[] args) throws Exception {

        // TODO: modify according to 'Aufgabenblatt8.md'.

        // TODO: declare and initialize 'Layered' variable.
        // Type 'RasterizedRGB' could be used as well...
        RasterizedRGB raster = new TreeSparseRasterRGBA(40, 60);
        Scanner sc = new Scanner(System.in);
        int cellSize = 10;
        CodeDraw cd = new CodeDraw(raster.getWidth() * cellSize, raster.getHeight() * cellSize);
        // set default color.
        Color[] c = {Color.GREEN};
        // The variable c contains the default color as an array entry. An array is
        // used because it enables the default color to be changed by other classes after
        // it has been passed to a factory object (multiple objects use identical array).
        cd.clear(Color.BLACK);
        String[] executedCommands = new String[1];

        String input =
            "create 30 40\n" +
            "line 2 3 10 20\n" +
            "newlayer\n" +
            "setcolor 255 0 255 255\n" +
            "line 0 10 17 7\n" +
            "newlayer\n" +
            "setcolor 255 0 0 255 \n" +
            "line 0 30 19 0\n" +
            "load data/Image.txt\n"+
            "save data/Image2.txt";
        var list = new ArrayList<String>();

        sc = new Scanner(input);

        // HashMap<String, SafeFactory> is a predefined associative data structure in the
        // Java libraries implemented using a hash table, where keys are of type 'String' and
        // associated values of type 'SafeFactory'.
        HashMap<String, SafeFactory> commandMap = new HashMap<String, SafeFactory>();
        commandMap.put("line", new SafeLineFactory(c));
        commandMap.put("create", new SafeCreateFactory());
        commandMap.put("load", new SafeLoadSequenceFactory(commandMap));
        commandMap.put("newlayer", new SafeNewLayerFactory());
        commandMap.put("save", new SafeSaveFactory(list));

        // TODO: put key-value associations to 'commandMap': keys are command strings (like "line"
        //  or "newlayer"), values are corresponding factories.
        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("setcolor")) {
                // command that does not involve a raster
                var r = sc.nextInt();
                var g = sc.nextInt();
                var b = sc.nextInt();
                var a = sc.nextInt();


                list.add("setcolor" + " " + r +  " " + g + " " + r + " "+ a );

                c[0] = new Color(r, g, b, a);
            } else {
                // commands with a corresponding factory object creating an operation on a raster.
                //TODO: modify the following block according to 'Aufgabenblatt5.md':
                //  use 'commandMap' to simplify.

                //  The following if-block can be replaced with just this single line:
                //
                var currentCommand = commandMap.get(command).create(sc);
                list.add(currentCommand.toString());
                currentCommand.execute(raster);

            }
            for (int j = 0; j < raster.getHeight(); j++) {
                for (int i = 0; i < raster.getWidth(); i++) {
                    int x = i * cellSize;
                    int y = j * cellSize;
                    cd.setColor(raster.getPixelColor(i, j));
                    cd.fillSquare(x, y, cellSize);
                }
            }
            cd.show();
        }
    }
}
