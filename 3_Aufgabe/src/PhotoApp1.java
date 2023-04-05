import codedraw.CodeDraw;
import java.awt.*;

public class PhotoApp1 {

    public static void main(String[] args) throws CloneNotSupportedException {
        SimpleRasterRGB r1 = new SimpleRasterRGB(40, 60);

        // TODO: change this method according to 'Aufgabenblatt1.md'.
        r1.drawLine(0, 1, 35, 9, new Color(20, 25, 250));
        r1.drawLine(30, 5, 0, 30, Color.ORANGE);
        r1.drawLine(2, 0, 7, 40, Color.GREEN);
        draw(r1);

        //Java gives me alot of Cancer why tf doesnt it tell me to point this garbo to a variable??
        //Tell me Mr Tutor?
        draw(r1.convolve(new double[][]{
                {0.077847, 0.123317, 0.077847},
                {0.123317, 0.195346, 0.123317},
                {0.077847, 0.123317, 0.077847}
        }));

        //Here some space for your answer:


    }

    // Draws the image with fixed pixel size in a new window.
    private static void draw(SimpleRasterRGB r1) {
        // TODO: change this method according to 'Aufgabenblatt1.md'.
        int cellSize = 10;
        CodeDraw cd = new CodeDraw(r1.getWidth() * cellSize, r1.getHeight() * cellSize);

        // draw a square of size 'cellSize' for each pixel
        for (int j = 0; j < r1.getHeight(); j++) {
            for (int i = 0; i < r1.getWidth(); i++) {
                int x = i * cellSize;
                int y = j * cellSize;
                cd.setColor(r1.getPixelColor(i, j));
                cd.fillSquare(x, y, cellSize);
            }
        }
        cd.show();
    }

    /*
    1. Was versteht man unter _Datenkapselung_? Geben Sie ein Beispiel, wo dieses Konzept in dieser
       Aufgabenstellung angewendet wird.
       Unter Datenkapselung versteht man das Gruppieren von Daten/Objekten von Daten.
       Seien die Fields Speed und Kg und diese Beschreiben eine Car Klasse, dann kann man diese in diese Klasse enkapsulieren
    2. Was versteht man unter _Data Hiding_? Geben Sie ein Beispiel, wo dieses Konzept in dieser
       Aufgabenstellung angewendet wird.
       Unter Data Hiding "verstecken" aka. die Restriktierung der Zugriffe von Daten.
       Sei Klasse Car mit Field Speed und Kg, dann kann Klasse Tier nicht auf diese Zugreifen, ohne auf Reflexions oder
       einen Getter zu verwenden
    3. Was steht bei einem Methodenaufruf links vom `.` (z.B. bei `SimpleRasterRGB.getPixelColor()` oder
       `r1.getPixelColor()`)? Woran erkennt man dabei Objektmethoden?
        Das eine ist der Aufruf von einer static Method und andere ist der Aufruf der Meth von einer Instanze. Man erkennt es
        an "SimpleRasterRGB", weil das eine Klasse ist.
    4. Wofür steht `this`?
        this bezieht sich auf das Current Class
    5. Erklären Sie den Unterschied zwischen den Methoden
       `public static SimpleRasterRGB convolve(SimpleRasterRGB toBeFiltered, double[][] filterKernel)`
       und `public SimpleRasterRGB convolve(double[][] filterKernel)`.
       Das erste ist eine static Method und das zweite ist der Meth von einer Instanz. Siehe 3)
    6. Kann innerhalb einer Objektmethode von `SimpleRasterRGB` auf die privaten Variablen eines
       anderen `SimpleRasterRGB` Objekts zugegriffen werden (z.B. `this.width = r.width;`)?
       Ja, wenn ich eine Methode in der Klasse SimpleRasterRGB mit dem Param SimpleRasterRGB. Vgl Nodes.
       public void setWidth(SimpleRasterRGB r){
       this.width = r.width;
    }
    * */
}
