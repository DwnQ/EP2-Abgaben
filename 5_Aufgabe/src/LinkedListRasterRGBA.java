// A list of objects of 'RasterRGBA' implemented as a doubly linked list.
// The number of elements of the list is not limited. Entries of 'null' are allowed.
//
// TODO: define further classes and methods for the implementation of the doubly linked list, if
//  needed.
//
public class LinkedListRasterRGBA {

    //TODO: declare variables.
    private RasterRGBA node;
    private LinkedListRasterRGBA nextNode;

    // Initializes 'this' as an empty list.
    public LinkedListRasterRGBA() {

        //TODO: define constructor.
    }

    // Inserts the specified element 'raster' at the beginning of this list.
    public void addFirst(RasterRGBA raster) {
        if (this.node == null) {
            this.node = raster;
            return;
        }
        var temp = new LinkedListRasterRGBA();
        temp.node = this.node;
        temp.nextNode = this.nextNode;
        this.node = raster;
        this.nextNode = temp;
    }

    // Appends the specified element 'raster' to the end of this list.
    public void addLast(RasterRGBA raster) {
        if(node == null){
            node = raster;
            return;
        }
        if(nextNode == null){
            var newNode = new LinkedListRasterRGBA();
            newNode.addFirst(raster);
            nextNode = newNode;
            return;
        }
        var temp = this;
        while (temp.nextNode != null){
            temp = temp.nextNode;
        }
        var newNode = new LinkedListRasterRGBA();
        newNode.addFirst(raster);
        temp.nextNode = newNode;
        //TODO: implement method.
    }

    // Returns the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getLast() {
        if(node== null){
            return null;
        }
        if(nextNode == null){
            return node;
        }
        var temp = this;
        while (temp.nextNode != null){
            temp = temp.nextNode;
        }
        //TODO: implement method.
        return temp.node;
    }

    // Returns the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA getFirst() {
        if(node== null){
            return null;
        }
        return node;
    }

    // Retrieves and removes the first element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollFirst() {
        if (node == null) {
            return null;
        }
        var res = node;
        if (nextNode == null) {
            node = null;
        } else {
            node = nextNode.node;
            nextNode = nextNode.nextNode;
        }
        return res;
    }

    // Retrieves and removes the last element in this list.
    // Returns 'null' if the list is empty.
    public RasterRGBA pollLast() {
        if (node == null) {
            return null;
        } else if (nextNode == null) {
            var res = node;
            node = null;
            return res;
        } else {
            var prevNode = this;
            var currNode = nextNode;
            while (currNode.nextNode != null) {
                prevNode = currNode;
                currNode = currNode.nextNode;
            }
            prevNode.nextNode = null;
            return currNode.node;
        }
    }

    // Inserts the specified element 'raster' at the specified position in this list.
    // More specifically, 'raster' is inserted as follows:
    // before insertion elements have indices from 0 to size()-1;
    // 'raster' is inserted immediately before the element with the given index 'i' (or as last
    // element if 'i == size()') such that 'raster' can be found at index 'i' after insertion.
    // Precondition: i >= 0 && i <= size().
    public void add(int i, RasterRGBA raster) {
        var newNode = new LinkedListRasterRGBA();
        newNode.node = raster;

        if (i == 0) {
            // Adding at the head of the list.
            newNode.nextNode = this;
            this.nextNode = newNode;
        } else {
            // Adding at a non-head position.
            var temp = this;
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
    public RasterRGBA get(int i) {
        if(i == 0){
            return node;
        }
        var currentNode = nextNode;
        for (int j = 1; j < i; j++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode.node;
    }

    // Replaces the element at the specified position in this list with the specified element.
    // Returns the element that was replaced.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA set(int i, RasterRGBA raster) {
        if (i == 0) {
            var out = node;
            node = raster;

            return out;
        } else {
            var temp = this;
            for (int k = 0; k < i && temp.nextNode != null; i++) {
                temp = temp.nextNode;
            }
            var out = temp.node;
            temp.node = raster;
            return out;
        }
    }

    // Removes the element at the specified position in this list. Shifts any subsequent
    // elements to the left (subtracts one from their indices). Returns the element that was
    // removed from the list.
    // Precondition: i >= 0 && i < size().
    public RasterRGBA remove(int i) {
        try {
            if(i == 0){
                var out = node;
                node = nextNode.node;
                nextNode = nextNode.nextNode;
                return out;
            }
            var currentNode = this;
            for (int j = 1; j < i && currentNode.nextNode != null; j++) {
                currentNode = currentNode.nextNode;
            }

            var out = currentNode.nextNode.node;
            currentNode.nextNode = currentNode.nextNode.nextNode;

            return out;
        }catch (Exception e){
            throw new NullPointerException(e.getMessage());
        }
    }

    // Returns the index of the last occurrence of 'raster' in this list (the highest index with an
    // element equal to 'raster'), or -1 if this list does not contain the element.
    // Equality of elements is determined by object identity (== operator).
    public int lastIndexOf(RasterRGBA raster) {
        int index = -1;
        boolean found = false;
        int i = 0;
        var temp = this;

        while (temp != null) {
            if (temp.node != null && temp.node.equals(raster)) {
                index = i;
                found = true;
            }
            i++;
            temp = temp.nextNode;
        }

        return found ? index : -1;
    }

    // Returns the number of elements in this list.
    public int size() {
        int counter = 0;

        if(node == null){
            return counter;
        }

        var temp = this;

        while (temp !=null){
            temp = temp.nextNode;
            counter++;
        }
        return counter;
    }

    //TODO (optional): add more operations (e.g., floodfill).
}

// TODO: define further classes, if needed (either here or in a separate file).
