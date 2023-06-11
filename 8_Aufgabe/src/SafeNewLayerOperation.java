public class SafeNewLayerOperation implements SafeOperationSingle // TODO: activate clause.
{
    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {
        try{

            raster = new MultiLayerRasterRGBA(new TreeSparseRasterRGBA(raster.getWidth(),raster.getHeight()), raster);
        }catch (Exception e){
            throw new OperationException();
        }
        return raster;
    }
    public String toString() {
        return "newlayer";
    }

    // TODO: define missing parts of this class.
}
