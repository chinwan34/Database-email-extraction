import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> listOfFileNames = new ArrayList<>();
        String[] fileNames = new String[] {
                "corrupteddb26", "corrupteddb27", "corrupteddb52",
                "corrupteddb97", "corrupteddb107", "corrupteddb150",
                "corrupteddb154", "corrupteddb176", "corrupteddb276",
                "corrupteddb286", "corrupteddb308", "corrupteddb396",
                "corrupteddb493", "corrupteddb591", "corrupteddb607",
                "corrupteddb643", "corrupteddb697", "corrupteddb763",
                "corrupteddb888", "corrupteddb918"
        };
        listOfFileNames.addAll(Arrays.asList(fileNames));

        SharedBuffer buffer = new SharedBuffer();
        EmailFinder finder = new EmailFinder(buffer, listOfFileNames.size());
        FileLoader loader = new FileLoader(listOfFileNames, buffer);

        loader.runThreads();
        finder.runThreads();

    }
}
