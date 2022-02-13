package exercise1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Consumer implements Runnable {
    Pipe.SourceChannel source; // Fonte, Origem
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    private long id;

    public Consumer(long id, Pipe.SourceChannel source) {
        this.source = source;
        this.id = id;
    }

    @Override
    public void run() {
        int sum = 0, value;
        while (true) {
            buffer.clear();
            try {
                source.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.flip();

            value = buffer.getInt();
            sum += value;
            System.out.println(id + " Consume: " + value + " " + sum);

            if (value == 0)
                break;
        }
    }
}
