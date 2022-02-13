package exercise2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;

public class Fork {
    PipedInputStream in;
    PipedOutputStream out;

    private int i;

    public Fork(int i) {
        this.i = i;

        try {
            out = new PipedOutputStream();
            in = new PipedInputStream(out, 1);
            in.mark(1);
            this.giveBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean take() throws IOException {
        return in.available() > 0 ? in.read() == 0 : false;
    }

    public synchronized void giveBack() throws IOException {
        out.write(0);
    }

    @Override
    public String toString() {
        return "Fork(" + i + ")";
    }
}
