public class SimplePointQueue {
    // TODO: declare variables.
    private Point[] queue;
    private int tail = 0;
    // Initializes this queue with an initial capacity (length of internal array).
    // Precondition: initialCapacity > 0.
    public SimplePointQueue(int initialCapacity) {
        queue = new Point[initialCapacity];
        //TODO: define constructor.
    }

    // Adds the specified point 'p' to this queue.
    // Precondition: p != null.
    public void add(Point p) {
        tail++;

        if(tail < queue.length){
            queue[tail-1] = p;
            return;
        }

        Point[] newQueue = new Point[queue.length<<1];
        for (int i = 0; i < queue.length; i++) {
            newQueue[i] = queue[i];
        }
        newQueue[queue.length]=p;
        queue = newQueue;
    }

    // Retrieves and removes the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point poll() {

        if (tail == 0) {
            return null;
        }

        Point temp = queue[0];
        for (int i = 0; i < tail - 1; i++) {
            queue[i] = queue[i+1];
        }
        tail--;
        queue[tail] = null;
        return temp;
    }

    // Retrieves, but does not remove the head of this queue, or returns 'null'
    // if this queue is empty.
    public Point peek() {
        if(queue[0] == (null)){//Should be not needed
            return null;
        }
        return queue[0];
    }

    // Returns the number of entries in this queue.
    public int size() {

        return tail;
    }
}
