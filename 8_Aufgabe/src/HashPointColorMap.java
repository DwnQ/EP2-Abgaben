import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

// A map that associates a position (objects of type 'Point') with a color (objects of type 'Color').
// The number of key-value pairs is not limited.
// The map is implemented as hash map. The map does not contain any keys or values being 'null'.
//
public class HashPointColorMap {

    private int size = 0;
    private Point[] points = new Point[16];
    private Color[] colors = new Color[16];

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null && value != null.
    public Color put(Point key, Color value) {
        if (!this.containsKey(key)) {
            if (size == points.length - 1) myDoubleArraySize();

            this.points[size] = key;
            this.colors[size] = value;
            size++;
            return null;
        }

        int index = myGetIndexOf(key);
        Color toReturn = colors[index];
        colors[index] = value;

        return toReturn;
    }

    private void myDoubleArraySize() {
        Point[] newPoints = new Point[points.length * 2]; // neues Array mit doppelter Größe erstellen
        Color[] newColors = new Color[colors.length * 2]; // neues Array mit doppelter Größe erstellen

        System.arraycopy(points, 0, newPoints, 0, points.length); // Daten vom alten ins neue Array kopieren
        System.arraycopy(colors, 0, newColors, 0, colors.length); // Daten vom alten ins neue Array kopieren

        this.points = newPoints;
        this.colors = newColors;
    }

    private int myGetIndexOf(Point key) {
        for (int i = 0; i < size; i++) {
            if (points[i] == null) return -1;
            if (points[i].equals(key)) return i;
        }
        return -1;
    }
    
    public int countCollisions(){
        int counter=0;
        for(int i = 0;i < points.length; i++){
            for(int k = 0;k < points.length; k++){
                if(i!=k){
                    if(points[i].hashCode()==points.hashCode()){ //hier sollten die berechneten indizes aus den hashwerten eigentlich verglichen werden
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with the specified point.
    // More formally, if this map contains a mapping from a key k to a value v such that
    // key.equals(k) == true, then this method returns v.
    // (There can be at most one such mapping.)
    // Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {
        int index = myGetIndexOf(key);
        return index == -1 ? null : colors[index];
    }

    // Removes the mapping for a key from this map if it is present. More formally, if this map
    // contains a mapping from key k to value v such that key.equals(k) == true,
    // that mapping is removed. (The map can contain at most one such mapping.)
    // Returns the value to which this map previously associated the key, or 'null' if the map
    // contained no mapping for the key.
    // Precondition: key != null.
    public Color remove(Point key) {
        int index = myGetIndexOf(key);
        if (index == -1 || size == 0) return null;

        Color toReturn = colors[index];
        points[index] = null;
        colors[index] = null;
        for (int i = index; i < size - 1; i++) {
            points[i] = points[i + 1];
            colors[i] = colors[i + 1];
        }

        size--;
        return toReturn;
    }

    // Returns a queue with all keys of this map (ordering is not specified).
    public SimplePointQueue keys() {
        SimplePointQueue q = new SimplePointQueue(size);
        for (int i = 0; i < size; i++) {
            q.add(this.points[i]);
        }

        return q;
    }

    // Returns 'true' if the specified key is contained in this map.
    // Returns 'false' otherwise.
    public boolean containsKey(Point key) {
        return myGetIndexOf(key) != -1;
    }

    // Returns 'true' if the specified value is contained at least once in this map.
    // Returns 'false' otherwise.
    public boolean containsValue(Color value) {
        for (int i = 0; i < size; i++) {
            if (colors[i] == null) return false;
            if (colors[i].equals(value)) return true;
        }
        return false;
    }

    // Returns a string representation of this map with key-value pairs in parentheses, separated
    // by commas (order is not specified).
    // Example: {([9, 2], java.awt.Color[r=255,g=255,b=0]), ([7, 1], java.awt.Color[r=255,g=0,b=0])}
    public String toString() {
        String s = "{";

        for (int i = 0; i < size; i++) {
            s += "(" + points[i].toString() + ", " + colors[i].toString() + "), ";
        }

        return s.substring(0, s.length() - 2) + "}";
    }

    // Returns 'true' if 'this' and 'o' are equal, meaning 'o' is of class 'HashPointColorMap'
    // and 'this' and 'o' contain the same key-value pairs, i.e. the number of key-value pairs is
    // the same in both maps and every key-value pair in 'this' equals one key-value pair in 'o'.
    // Two key-value pairs are equal if the two keys are equal and the two values are equal.
    // Otherwise, 'false' is returned.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashPointColorMap map = (HashPointColorMap) o;
        return Arrays.equals(points, map.points) && Arrays.equals(colors, map.colors);
    }

    @Override
    // Returns the hash code of 'this'.
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(points);
        result = 31 * result + Arrays.hashCode(colors);
        return result;
    }
}

