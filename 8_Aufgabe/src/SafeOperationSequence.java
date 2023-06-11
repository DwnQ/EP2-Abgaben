import java.util.*;

// Represents a succession of two operations. Each of which can be itself of type
// 'SafeOperationSequence' such that this class represents a recursive (tree-like)
// structure. The foundation (leafs of the tree) is represented by objects of
// 'SafeSingleOperation'.
public class SafeOperationSequence implements SafeOperation, SafeOperationIterable {

    //TODO: define missing parts of this class.
    private SafeOperation first;
    private SafeOperation second;

    public SafeOperationSequence(SafeOperation first, SafeOperation second) {
        this.first = first;
        this.second = second;
        //TODO: implement constructor.
    }

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {

        if(raster == null){
            throw new OperationException();
        }

        var listWithOperations = new ArrayList<SafeOperationSingle>();
        addToList(this,listWithOperations);
        SafeOperationSingle n = listWithOperations.get(0);
        RasterizedRGB newRaster = n.execute(raster);
        for (int i = 1; i < listWithOperations.size() ; i++) {

            n = listWithOperations.get(i);
            if(n instanceof SafeNewLayerOperation){
                newRaster = n.execute(newRaster);
            } else {
                n.execute(newRaster);
            }


        }
        return newRaster;
    }

    public SafeOperation getFirst() {

        //TODO: implement method.
        return first;
    }

    public SafeOperation getSecond() {

        //TODO: implement method.
        return second;
    }

    public void addToList(SafeOperation node,List<SafeOperationSingle> list){
        if(node == null){
            return;
        }
        if(!(node instanceof SafeOperationSingle) && ((SafeOperationSequence)node).getFirst() != null){
            addToList(((SafeOperationSequence) node).getFirst(),list);
        }

        if(node instanceof SafeOperationSingle ){
            list.add((SafeOperationSingle) node);
        }

        if(!(node instanceof SafeOperationSingle) && ((SafeOperationSequence)node).getSecond() != null){
            addToList(((SafeOperationSequence)node).getSecond(),list);
        }
    }
    @Override
    public SafeOperationIterator iterator() {
        var list = new ArrayList<SafeOperationSingle>();

        addToList(this, list);
        //TODO: implement method.
        return new OperationIterator(list.iterator());
    }

    @Override
    public String toString() {
        var it = iterator();
        String out = "";
        while (it.hasNext()){
            out +=it.next()+"\n";
        }
        return out;
    }
}

class OperationIterator implements SafeOperationIterator{

    private Iterator<SafeOperationSingle> it;

    public OperationIterator(Iterator<SafeOperationSingle> iterator) {
        it = iterator;
    }


    @Override
    public SafeOperationSingle next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

}
//TODO: additional classes for the implementation of the iterator.
