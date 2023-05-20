import codedraw.CodeDraw;

import java.awt.*;
import java.nio.channels.Pipe;
import java.util.Arrays;

public class SimplePointQueue {

    // TODO: declare variables.
    private Point[] points;
    private int pointer; //points to the last element in the queue

    // Initializes this queue with an initial capacity (length of internal array).
    // Precondition: initialCapacity > 0.
    public SimplePointQueue(int initialCapacity) {
        this.points = new Point[initialCapacity];
        this.pointer = 0;
    }

    // Adds the specified point 'p' to this queue.
    // Precondition: p != null.
    public void add(Point p) {

        if (points[points.length - 1] != null) {
            this.doublePointsArray();
        }

//        for (int i = 0; i < pointer; i++) {
//            if (points[i].compareTo(p) == 0) return;
//        }


        points[pointer] = p;
        pointer++;
    }

    private void doublePointsArray() {
        Point[] newPoints = new Point[points.length * 2];
        for (int i = 0; i <= points.length - 1; i++) {
            newPoints[i] = points[i];
        }
        this.points = newPoints;
    }

    // Retrieves and removes the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point poll() {

        if (points[0] == null) return null;

        Point toReturn = points[0];

        for (int i = 0; i < points.length - 1; i++) {
            points[i] = points[i + 1];
        }
        points[points.length - 1] = null;
        pointer--;

        return toReturn;
    }

    // Retrieves, but does not remove the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point peek() {
        return this.points[0];
    }

    // Returns the number of entries in this queue.
    public int size() {
        return this.pointer;
    }

    public SimplePointQueue copy() {
        SimplePointQueue q = new SimplePointQueue(this.pointer);
        for (Point p : this.points) {
            q.add(p);
        }
        return q;
    }

    @Override
    public String toString() {
        return Arrays.toString(points);
    }
}
