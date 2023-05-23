// A list of objects of 'RasterRGBA' implemented as a doubly linked list.
// The number of elements of the list is not limited. Entries of 'null' are allowed.
//
// TODO: define further classes and methods for the implementation of the doubly linked list, if
//  needed.
//
public class LinkedListRasterRGBA {

    //TODO: declare variables.
    private MyDoubleListNode head;
    private MyDoubleListNode tail;

    // Initializes 'this' as an empty list.
    public LinkedListRasterRGBA() {

        //TODO: define constructor.
    }

    // Inserts the specified element 'raster' at the beginning of this list.
    public void addFirst(RasterRGBA raster) {

        //TODO: implement method.
        if (head == null) {
            head = tail = new MyDoubleListNode(raster, null, null);
        } else if (head == tail) {
            head = new MyDoubleListNode(raster, null, head);
            tail.setPrev(head);
        } else {
            head = new MyDoubleListNode(raster, null, head);
            head.getNext().setPrev(head);
        }
    }

    // Appends the specified element 'raster' to the end of this list.
    public void addLast(RasterRGBA raster) {

        //TODO: implement method.
        if (head == null) {
            head = tail = new MyDoubleListNode(raster, null, null);
        } else if (head == tail) {
            tail = new MyDoubleListNode(raster, tail, null);
            head.setNext(tail);
        }
        else {
            tail = new MyDoubleListNode(raster, tail, null);
            tail.getPrev().setNext(tail);
        }
    }

    // Returns the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getLast() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }
        return tail.getRaster();
    }

    // Returns the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getFirst() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }
        return head.getRaster();
    }

    // Retrieves and removes the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollFirst() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }
        RasterRGBA toReturnRaster = head.getRaster();
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrev(null);
        }
        return toReturnRaster;
    }

    // Retrieves and removes the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollLast() {

        //TODO: implement method.
        if (head == null) {
            return null;
        }
        RasterRGBA toReturnRaster = tail.getRaster();
        tail = tail.getPrev();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        return toReturnRaster;
    }

    // Inserts the specified element 'raster' at the specified position in this list.
    // More specifically, 'raster' is inserted as follows:
    // before insertion elements have indices from 0 to size()-1;
    // 'raster' is inserted immediately before the element with the given index 'i' (or as last
    // element if 'i == size()') such that 'raster' can be found at index 'i' after insertion.
    // Precondition: i >= 0 && i <= size().
    public void add(int i, RasterRGBA raster) {

        //TODO: implement method.
        if (i == 0) {
            addFirst(raster);
        } else if (i == size()) {
            addLast(raster);
        } else {
            head.add(raster, i);
        }
    }

    // Returns the element at the specified position in this list.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA get(int i) {

        //TODO: implement method.
        return head.get(i);
    }

    // Replaces the element at the specified position in this list with the specified element.
    // Returns the element that was replaced.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA set(int i, RasterRGBA raster) {

        //TODO: implement method.
        return head.set(i, raster);
    }

    // Removes the element at the specified position in this list. Shifts any subsequent
    // elements to the left (subtracts one from their indices). Returns the element that was
    // removed from the list.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA remove(int i) {

        //TODO: implement method.
        if (head == tail || i == size() - 1) {
            return pollLast(); // Assuming preconditions hold
        }
        if (i == 0) {
            return pollFirst();
        }
        return head.remove(i);
    }

    // Returns the index of the last occurrence of 'raster' in this list (the highest index with an
    // element equal to 'raster'), or -1 if this list does not contain the element.
    // Equality of elements is determined by object identity (== operator).
    public int lastIndexOf(RasterRGBA raster) {

        //TODO: implement method.
        if (tail == null) {
            return -1;
        }
        return tail.lastIndexOf(raster, size()-1);
    }

    // Returns the number of elements in this list.
    public int size() {

        //TODO: implement method.
        if (head == null) {
            return 0;
        }
        return head.size();
    }

    //TODO (optional): add more operations (e.g., floodfill).
}

// TODO: define further classes, if needed (either here or in a separate file).

//class implementing the linked list
class MyDoubleListNode {
    private RasterRGBA b;
    private MyDoubleListNode next;
    private MyDoubleListNode prev;

    MyDoubleListNode(RasterRGBA b, MyDoubleListNode prev, MyDoubleListNode next) {
        this.b = b;
        this.prev = prev;
        this.next = next;
    }

    RasterRGBA getRaster() {
        return b;
    }

    //Precondition: inner index
    void add(RasterRGBA b, int i) {
        if (i == 0) {
            //insert
            MyDoubleListNode newNode = new MyDoubleListNode(b, prev, this);
            prev.next = newNode;
            this.prev = newNode;
        } else {
            next.add(b, i - 1);
        }
    }

    // Precondition: 'i' is a valid index.
    RasterRGBA get(int i) {
        if (i == 0) {
            return b;
        } else {
            return next.get(i - 1);
        }
    }

    // Precondition: 'i' is a valid index.
    RasterRGBA set(int i, RasterRGBA raster) {
        if (i == 0) {
            RasterRGBA result = b;
            this.b = raster;
            return result;
        } else {
            return next.set(i - 1, raster);
        }
    }

    RasterRGBA remove(int i) {
        if (i == 0) {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            return this.b;
        } else {
            return next.remove(i - 1);
        }
    }

    int lastIndexOf(RasterRGBA raster, int i) {
        if (raster == this.b) {
            return i;
        }
        if (prev == null) {
            return -1;
        }
        return prev.lastIndexOf(raster, i - 1);
    }

    int size() {
        if (next == null) {
            return 1;
        }
        return 1 + next.size();
    }

    public MyDoubleListNode getNext() {
        return next;
    }

    public MyDoubleListNode getPrev() {
        return prev;
    }

    void setNext(MyDoubleListNode node) { next = node; }

    void setPrev(MyDoubleListNode node) { prev = node; }

}
