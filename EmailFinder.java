import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class EmailFinder {
    private SharedBuffer buffer;
    private static int NUM_THREADS;
    private static final int MAX_T = 2;
    public int count;
    public ArrayList<String> allEmails;

    public EmailFinder(SharedBuffer buffer, int bufferSize) {
        this.buffer = buffer;
        NUM_THREADS = bufferSize;
        count = 0;
        allEmails = new ArrayList<>();
    }

    public void runThreads() throws InterruptedException {
        EmailWorker[] workers = new EmailWorker[NUM_THREADS];
        // Thread[] threads = new Thread[NUM_THREADS];

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        System.out.println("HI" + NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            workers[i] = new EmailWorker(buffer);
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            pool.execute(workers[i]);
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(1, TimeUnit.MINUTES)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            System.out.println("The number of addresses are: " + count);
            for (int i = 0; i < NUM_THREADS; i++) {
                System.out.println("Did we reach here?");
                count += workers[i].getCount();
                allEmails.addAll(workers[i].getEmails());
            }
        }

    }

}
