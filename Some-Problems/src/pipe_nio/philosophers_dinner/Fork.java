package pipe_nio.philosophers_dinner;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

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

    public boolean take() throws IOException {
        if (in.available() != 0) {
            in.read();
            return true;
        }
        return false;
    }

    public void giveBack() throws IOException {
        out.write(0);
    }

    @Override
    public String toString() {
        return "Fork(" + i + ")";
    }
}
