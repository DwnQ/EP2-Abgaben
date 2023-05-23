class LayerIterator implements RasterizedRGBIterator {
    private SingleLayer nextData;
    private Layered remainingData;

    public LayerIterator(Layered data) {
        this.nextData = data.getForeground();
        this.remainingData = data.getBackground();
    }

    @Override
    public RasterizedRGB next() {
        if (!hasNext()) return null;

        SingleLayer toReturn = nextData;

        nextData = remainingData == null ? null : remainingData.getForeground();
        remainingData =  remainingData == null ? null : remainingData.getBackground();
        return toReturn;
    }

    @Override
    public boolean hasNext() {
        return this.nextData != null;
    }
}