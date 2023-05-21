public class LayerIterator implements RasterizedRGBIterator {
    private SingleLayer data;
    private Layered next;

    public LayerIterator(Layered layered) {
        this.data = layered.getForeground();
        this.next = layered.getBackground();
    }

    @Override
    public RasterizedRGB next() {
        if (!hasNext()) return null;

        SingleLayer toReturn = data;

        data = next == null ? null : next.getForeground();
        next = next instanceof SingleLayer || next == null ? null : next.getBackground();

        return toReturn;
    }

    @Override
    public boolean hasNext() {
        return this.data != null;
    }
}
