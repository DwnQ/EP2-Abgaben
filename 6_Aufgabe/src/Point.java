import java.awt.*;
import java.util.Objects;

// A class for representing points with integer coordinates in 2D.
public class Point implements Comparable<Point>{
    private final int x, y;

    // Initializes this point with its coordinates.
    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    // Returns the x-coordinate of this point.
    public int getX() {

        return x;
    }

    // Returns the y-coordinate of this point.
    public int getY() {

        return y;
    }

    // Compares this point with a specified point. Defines an order relation ("less-than"
    // relation) on objects of 'Point'.  . Returns -1 if 'this' is less than 'p' or 1 otherwise.
    // Precondition: p != null
    public int compareTo(Point p) {
        // TODO: implement method.

        if (x < p.x) {
            return -1;
        }
        if (x > p.x) {
            return 1;
        }
        if (y < p.y) {
            return -1;
        }
        if (y > p.y) {
            return 1;
        }
        return 0;
    }


    @Override
    // Returns 'true' if 'o' is of class 'Point' and has coordinates equal to those of 'this'.
    // (This means that for two objects p1 and p2 of 'Point', p1.equals(p2) == true if and only if
    // p1.compareTo(p2) == 0.)
    // Return 'false' otherwise.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return this.compareTo(point) == 0;
    }

    @Override
    // Returns the hash code of 'this'.
    public int hashCode() {
        return Objects.hash(x,y);
        //TODO: implement method.
    }

    @Override
    // Returns a string representation of 'this'.
    public String toString() {

        return "["+getX()+", "+getY()+"]";
    }
}
