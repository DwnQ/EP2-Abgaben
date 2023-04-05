import java.awt.*;

// A map that associates a position (objects of type 'Point') with a color (objects of type 'Color'). The number of
// key-value pairs is not limited.
// The map is implemented as a binary tree. The keys are ordered based on the 'compareTo' method of 'Point'.
// The map does not contain any keys being 'null'.
//
// TODO: define further classes and methods for the implementation of the binary search tree,
//  if needed.
//
public class TreePointColorMap {
    //TODO: declare variables.
    private TreeNode root;

    // Adds a new key-value association to this map. If the key already exists in this map,
    // the value is replaced and the old value is returned. Otherwise, 'null' is returned.
    // Precondition: key != null.
    public Color put(Point key, Color value) {
        var temp = root;
        if(root == null){
            root = new TreeNode(key, value);
            return null;
        }
        while (true){
            if(key.compareTo(temp.key) == -1){
                if(temp.left == null){
                    temp.left = new TreeNode(key, value);
                    return null;
                }
                temp = temp.left;
            }
            else if(key.compareTo(temp.key) == 0){
                var out = temp.value;
                temp.value = value;
                return out;
            }
            else if(key.compareTo(temp.key) == 1) {
                if(temp.right == null){
                    temp.right = new TreeNode(key, value);
                    return null;
                }
                temp = temp.right;
            }
        }
    }


    // Returns the value associated with the specified key, i.e. the method returns the color
    // associated with coordinates specified by key (the key must have the same coordinates as the
    // specified 'key'). Returns 'null' if the key is not contained in this map.
    // Precondition: key != null.
    public Color get(Point key) {
        TreeNode node = getNode(key);
        return node == null ? null : node.value;
    }

    // Returns 'true' if this map contains a mapping for the specified key, this means
    // for a point with the same coordinates as the specified 'key'.
    // Precondition: key != null.
    public boolean containsKey(Point key) {
        return getNode(key) != null;
    }
    private TreeNode getNode(Point key) {
        TreeNode node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }
    // Returns a list with all keys of this map ordered ascending according to the
    // key order relation.

    public PointLinkedList keys() {
        PointLinkedList list = new PointLinkedList();
        recursiveTraversal(root, list);
        return list;
    }
    private void recursiveTraversal(TreeNode node, PointLinkedList list) {
        if (node != null) {
            recursiveTraversal(node.left, list);
            list.addLast(node.key);
            recursiveTraversal(node.right, list);
        }
    }

    // Returns a new raster representing a region with the specified size from this
    // map. The upper left corner of the region is (0,0) and the lower right corner is (width-1, height-1).
    // All pixels outside the specified region are cropped (not included).
    // Preconditions: width > 0 && height > 0
    public SimpleRasterRGB asRasterRGB(int width, int height) {
        var raster = new SimpleRasterRGB(width, height);
        var key = keys();
        while(key.size()>0) {
            var point = key.pollFirst();
            raster.setPixelColor(point.getX(), point.getY(), get(point));
        }
        return raster;
    }

    private class TreeNode {
        private Point key;
        private Color value;
        private TreeNode left, right;

        public TreeNode(Point key, Color value) {
            this.key = key;
            this.value = value;
        }
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

