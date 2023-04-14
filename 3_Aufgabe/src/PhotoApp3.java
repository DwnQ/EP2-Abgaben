import codedraw.CodeDraw;
import java.awt.*;
import java.util.Scanner;

// The executable class for 'Aufgabenblatt3.md'.
public class PhotoApp3 {

    public static void main(String[] args) throws CloneNotSupportedException {

        SimpleRasterRGB raster = new SimpleRasterRGB(40, 60);
        int cellSize = 10;
        CodeDraw cd = new CodeDraw(raster.getWidth() * cellSize, raster.getHeight() * cellSize);
        cd.clear(Color.BLACK);

        Scanner sc = new Scanner(System.in);
        // set default color.
        Color c = Color.GREEN;

        // set the filter kernel for blurring.
        double[][] filterKernel = new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        };

        // loop for interactive drawing:
        // reads a command and its parameters from System.in and draws the resulting
        // raster in a canvas. Feel free to modify.
        while(sc.hasNext()) {
            String command = sc.next();

            if (command.equals("line")) {

                // draw a line in the raster.
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                raster.drawLine(x1,y1,x2,y2,c);

            } else if (command.equals("filter")) {

                // filter the raster using the above kernel.

                raster = raster.convolve(filterKernel);


            } else if (command.equals("crop")) {

                // crop the raster to the rectangular region with upper left coordinates (0,0)
                // and lower right coordinates (width-1, height-1).
                int width = sc.nextInt();
                int height = sc.nextInt();

                TreePointColorMap tempMap = raster.asMap();
                PointLinkedList keys = tempMap.keys();
                SimpleRasterRGB tempRaster = new SimpleRasterRGB(width,height);
                for (int i = 0; i < keys.size(); i++) {
                    Point point = keys.get(i);
                    if (point.getX() < width && point.getY() < height) {
                        tempRaster.setPixelColor(point.getX(), point.getY(), tempMap.get(point));
                    }
                }
                raster = tempRaster;
                // TODO: fill in missing code.

                cd.close();
                cd = new CodeDraw(raster.getWidth() * cellSize, raster.getHeight() * cellSize);
            } else if (command.equals("setcolor")) {

                // set a new color for following drawing.
                c = new Color(sc.nextInt(),sc.nextInt(),sc.nextInt());
            }

            // draw a square of size 'cellSize' for each pixel
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
