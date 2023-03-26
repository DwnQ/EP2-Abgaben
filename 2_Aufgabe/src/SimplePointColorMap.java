import java.awt.*;

// A map that associates a position with a color. The number of key-value pairs is not limited.
// The map does not contain any keys or values being 'null'.
//
public class SimplePointColorMap {
    //TODO: declare variables.
    private Color[] colors;
    private Point[] keys;
    private int top;

    // Initializes this map with an initial capacity (length of internal array).
    // Precondition: initialCapacity > 0.
    public SimplePointColorMap(int initialCapacity) {
        colors = new Color[initialCapacity];
        keys = new Point[initialCapacity];
    }

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null && value != null.
    public Color put(Point key, Color value) {
        for (int i = 0; i < top; i++) {
            if(this.keys[i].compareTo(key)==0){
                Color duplicate = colors[i];
                colors[i] = value;
                return duplicate;
            }
        }
        if(top >= keys.length){
            doubleItAndGiveItToTheNextPerson();
        }
        colors[top] =value;
        keys[top] = key;
        top++;

        return null;

    }
    private void doubleItAndGiveItToTheNextPerson(){
        Color[] extendedColor = new Color[colors.length<<1];
        Point[] extendedKey = new Point[keys.length<<1];

        for (int i = 0; i < top; i++) {
            extendedColor[i]= colors[i];
            extendedKey[i]= keys[i];
        }
        colors = extendedColor;
        keys =  extendedKey;
    }

    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with the specified point.
    // More formally, if this map contains a mapping from a key k to a value v such that
    // key.compareTo(k) == 0, then this method returns v.
    // (There can be at most one such mapping.)
    // Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {
        for (int i = 0; i < top; i++) {
            if(keys[i].compareTo(key) == 0){
                return colors[i];
            }
        }
        return null;
    }

    // Removes the mapping for a key from this map if it is present. More formally, if this map
    // contains a mapping from key k to value v such that key.compareTo(k) == 0,
    // that mapping is removed. (The map can contain at most one such mapping.)
    // Returns the value to which this map previously associated the key, or 'null' if the map
    // contained no mapping for the key.
    // Precondition: key != null.
    public Color remove(Point key) {
        for (int i = 0; i < top; i++) {
            if (keys[i].compareTo(key) == 0) {
                Color removedColor = colors[i];
                // Shift elements to fill the gap
                for (int j = i; j < top; j++) {
                    keys[j] = keys[j+1];
                    colors[j] = colors[j+1];
                }

                top--;
                return removedColor;
            }
        }
        return null; // Key not found
    }

    // Returns a queue with all keys of this map (ordering is not specified).
    public SimplePointQueue keys() {
        //TODO: implement method.
        SimplePointQueue out = new SimplePointQueue(top) ;
        for (int i = 0; i < top; i++) {
            out.add(keys[i]);
        }
        return out;
    }
}