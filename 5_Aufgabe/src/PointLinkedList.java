// A list of 2D-points (objects of type 'Point') implemented as a linked list.
// The number of elements of the list is not limited.
//
// TODO: define further classes and methods for the implementation of the linked list,
//  if needed.
//
public class PointLinkedList {
    private PointLinkedList nextNode;
    private Point point;
    //TODO: declare variables.

    // Initializes 'this' as an empty list.
    public PointLinkedList() {
        nextNode = null;
        point = null;
    }

    // Inserts the specified element 'point' at the beginning of this list.
    // Precondition: point != null.
    public void addFirst(Point point) {
        if (this.point == null) {
            this.point = point;
            return;
        }
        var temp = new PointLinkedList();
        temp.point = this.point;
        temp.nextNode = this.nextNode;
        this.point = point;
        this.nextNode = temp;
    }

    // Appends the specified element 'point' to the end of this list.
    // Precondition: point != null.
    public void addLast(Point point) {
        if(this.point == null){
            this.point = point;
            return;
        }
        if(nextNode == null){
            var temp = new PointLinkedList();
            temp.point = point;
            nextNode =  temp;
            return;
        }
        nextNode.addLast(point);
    }

    // Returns the last element in this list.
    // Returns 'null' if the list is empty.
    public Point getLast() {
        if(point != null && nextNode != null){
            return nextNode.getLast();
        }
        if(nextNode == null){
            return point;
        }
        //TODO: implement method.
        return null;
    }

    // Returns the first element in this list.
    // Returns 'null' if the list is empty.
    public Point getFirst() {
        if(point == null){
            return null;
        }
        //TODO: implement method.
        return point;
    }

    // Retrieves and removes the first element in this list.
    // Returns 'null' if the list is empty.
    public Point pollFirst() {
        if (point == null) {
            return null;
        }
        Point res = point;
        if (nextNode == null) {
            point = null;
        } else {
            point = nextNode.point;
            nextNode = nextNode.nextNode;
        }
        return res;
    }

    // Retrieves and removes the last element in this list.
    // Returns 'null' if the list is empty.
    public Point pollLast() {
        if (point == null) {
            return null;
        } else if (nextNode == null) {
            Point res = point;
            point = null;
            return res;
        } else {
            PointLinkedList prevNode = this;
            PointLinkedList currNode = nextNode;
            while (currNode.nextNode != null) {
                prevNode = currNode;
                currNode = currNode.nextNode;
            }
            prevNode.nextNode = null;
            return currNode.point;
        }
    }

    // Inserts the specified element 'point' at the specified position in this list.
    // Precondition: i >= 0 && i <= size() && point != null.
    public void add(int i, Point point) {
        PointLinkedList newNode = new PointLinkedList();
        newNode.point = point;

        if (i == 0) {
            // Adding at the head of the list.
            newNode.nextNode = this;
            this.nextNode = newNode;
        } else {
            // Adding at a non-head position.
            PointLinkedList temp = this;
            i--;
            for (int k = 0; k < i && temp.nextNode != null; i++) {
                temp = temp.nextNode;
            }
            newNode.nextNode = temp.nextNode;
            temp.nextNode = newNode;
        }
    }

    // Returns the element at the specified position in this list.
    // Precondition: i >= 0 && i < size().
    public Point get(int i) {
        if(i == 0){
            return point;
        }
        PointLinkedList currentNode = nextNode;
        for (int j = 1; j < i; j++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode.point;
    }

    // Returns the index of the first occurrence (element with equal coordinates to 'point') of the
    // specified element in this list, or -1 if this list does not contain the element.
    // Precondition: point != null.
    public int indexOf(Point point) {
        if(this.point.compareTo(point)==0){
            return 0;
        }
        if(this.point.compareTo(nextNode.point)==0){
            return 1;
        }
        //TODO: implement method.
        return 1 + nextNode.indexOf(point);
    }

    // Returns the number of elements in this list.
    public int size() {
        if(point == null){
            return 0;
        }
        if(nextNode == null){
            return 1;
        }
        //TODO: implement method.
        return 1+nextNode.size();
    }

    @Override
    public String toString() {
        return "PointLinkedList{" +
                "nextNode=" + nextNode +
                ", point=" + point +
                '}';
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
