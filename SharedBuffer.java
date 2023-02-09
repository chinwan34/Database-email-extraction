import java.util.ArrayList;

public class SharedBuffer {
    private ArrayList<String> buffer;
    public int fixedLength;

    public SharedBuffer() {
        buffer = new ArrayList<>();

    }

    public void append(String chunks) {
        buffer.add(chunks);
    }

    public String returnChunk() {
        if (buffer.size() != 0) {
            System.out.println("Size of buffer" + buffer.size());
            String chunk = buffer.get(0);
            buffer.remove(0);
            return chunk;
        } else {
            return null;
        }
    }

    public int getBufferSize() {
        return buffer.size();
    }

    public ArrayList<String> getBuffer() {
        return buffer;
    }

}
