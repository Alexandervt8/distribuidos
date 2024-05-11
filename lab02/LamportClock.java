public class LamportClock {
    private int clock;

    public LamportClock() {
        this.clock = 0;
    }

    public synchronized int tick() {
        this.clock++;
        return this.clock;
    }

    public synchronized void update(int receivedTime) {
        this.clock = Math.max(this.clock, receivedTime) + 1;
    }

    public int getTime() {
        return this.clock;
    }
}
