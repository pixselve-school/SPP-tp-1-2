public class Semaphore implements SemaphoreInterface {
    private int permits;

    public Semaphore() {
        permits = 0;
    }

    @Override
    public synchronized void up() {
        permits++;
        notify();
    }

    @Override
    public synchronized void down() {
        while (permits == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        permits--;
    }

    @Override
    public synchronized int releaseAll() {
        int released = permits;
        permits = 0;
        notifyAll();
        return released;
    }
}
