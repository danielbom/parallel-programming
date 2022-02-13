package reader_writer.v3;

import java.util.concurrent.Semaphore;

import light_switch.LightSwitch;

public class ReaderWriter {
    private final Semaphore wlock = new Semaphore(1);
    private final LightSwitch lightSwitch = new LightSwitch();

    public void startRead() throws InterruptedException {
        lightSwitch.lock(wlock);
    }

    public void endRead() throws InterruptedException {
        lightSwitch.unlock(wlock);
    }

    public void startWrite() throws InterruptedException {
        wlock.acquire();
    }

    public void endWrite() throws InterruptedException {
        wlock.release();
    }
}
