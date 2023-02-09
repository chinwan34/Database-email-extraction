import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileLoader {
    private SharedBuffer buffer;
    private static int NUM_THREADS;
    private ArrayList<String> pathList;
    private static final int MAX_T = 3;

    public FileLoader(ArrayList<String> pathList, SharedBuffer buffer) {
        this.pathList = pathList;
        this.buffer = buffer;
        NUM_THREADS = pathList.size();
    }

    public void runThreads() throws InterruptedException {
        FileWorker[] workers = new FileWorker[NUM_THREADS];
        // Thread[] threads = new Thread[NUM_THREADS];

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        for (int i = 0; i < NUM_THREADS; i++) {
            workers[i] = new FileWorker(buffer, pathList.get(i));
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            pool.execute(workers[i]);
        }
        pool.shutdown();
    }

}
