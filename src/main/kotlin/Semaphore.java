public class Semaphore implements SemaphoreInterface {
    private int permits;
    private int blockedThreads;

    public Semaphore() {
        this.permits = 0;
        this.blockedThreads = 0;
    }

    public synchronized void up() {
        permits++;
        if (blockedThreads > 0) {
            blockedThreads--;
            this.notify();
        }
    }

    public synchronized void down() {
        while (permits == 0) {
            try {
                blockedThreads++;
                this.wait();
            } catch (InterruptedException e) {
                // Handle the exception or rethrow it as needed
                Thread.currentThread().interrupt();
            }
        }
        permits--;
    }

    public synchronized int releaseAll() {
        int blockedThreads = this.blockedThreads;
        this.blockedThreads = 0;
        this.permits += blockedThreads;
        this.notifyAll();

        return blockedThreads;
    }
}
