import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void write(String output) {
        FileWriter writer = null;
        try {
            writer  = new FileWriter("monitoring.txt",true);
            writer.write(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}