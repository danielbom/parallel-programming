package reader_writer.v2;

public class ReaderWriter {
    int numReaders = 0;
    boolean hasWriter = false;

    public synchronized void startRead() {
        while (hasWriter) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        numReaders++;
    }

    public synchronized void endRead() {
        numReaders--;
        if (numReaders == 0)
            this.notify();
    }

    public synchronized void startWrite() {
        while (numReaders != 0 || hasWriter) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        hasWriter = true;
    }

    public synchronized void endWrite() {
        hasWriter = false;
        this.notifyAll();
    }
}
