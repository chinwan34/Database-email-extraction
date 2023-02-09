
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileWorker implements Runnable {
    public SharedBuffer buffer;
    public String filePath;
    public String output;

    public FileWorker(SharedBuffer buffer, String filePath) {
        this.buffer = buffer;
        this.filePath = filePath;
    }

    public String getOutputData() {
        return output;
    }

    @Override
    public void run() {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            output = sb.toString();
            buffer.append(output);
            System.out.println(buffer.getBufferSize());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}