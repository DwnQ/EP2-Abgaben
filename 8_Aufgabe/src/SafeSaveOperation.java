import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SafeSaveOperation implements SafeOperationSingle {

    private final String path;
    private final String data;

    public SafeSaveOperation(String path, String data) {
        this.path = path;
        this.data = data;
    }

    @Override
    public String toString() {
        return "save " + path;
    }

    @Override
    public RasterizedRGB execute(RasterizedRGB raster) throws OperationException {

        File file = new File(path);

        try {
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(data.getBytes());
            fos.close();

            System.out.println("File overwritten successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while overwriting the file: " + e.getMessage());
            e.printStackTrace();
        }


        return raster;
    }
}
