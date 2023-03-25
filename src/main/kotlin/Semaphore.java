public class Semaphore implements SemaphoreInterface {
    private int permits;
    private int waitingThreads;

    public Semaphore() {
        this.permits = 0;
        this.waitingThreads = 0;
    }

    public synchronized void up() {
        permits++;
        if (waitingThreads > 0) {
            this.notify();
        }
    }

    public synchronized void down() {
        while (permits == 0) {
            try {
                waitingThreads++;
                this.wait();
                waitingThreads--;
            } catch (InterruptedException e) {
                // Handle the exception or rethrow it as needed
                Thread.currentThread().interrupt();
            }
        }
        permits--;
    }

    public synchronized int releaseAll() {
        int unblockedThreads = waitingThreads;
        if (waitingThreads > 0) {
            permits = waitingThreads;
            this.notifyAll();
        }
        return unblockedThreads;
    }
}
