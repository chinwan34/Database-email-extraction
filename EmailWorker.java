import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class EmailWorker implements Runnable {
    private SharedBuffer buffer;
    private String chunk;
    private ArrayList<String> allEmails;
    private int count;

    public EmailWorker(SharedBuffer buffer) {
        this.buffer = buffer;
        allEmails = new ArrayList<>();
    }

    public void getChunk() {
        chunk = buffer.returnChunk();
    }

    public ArrayList<String> getEmails() {
        return allEmails;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true) {
            getChunk();

            if (chunk == null) {
                System.out.println("No data to use");
                return;
            }

            Matcher m = Pattern.compile(
                    "[_a-zA-Z]+[_a-zA-Z0-9]?[\\._]?[_a-zA-Z0-9]*@([a-zA-Z]+\\.)?([a-zA-Z]+\\.)?[a-zA-Z]+\\.(com|net|de|uk|ro|jp)")
                    .matcher(chunk);
            while (m.find()) {
                allEmails.add(m.group());
            }

            count = allEmails.size();
            System.out.println("Count" + count);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
