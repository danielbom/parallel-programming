package exercise1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Produtor implements Runnable {
    Pipe.SinkChannel sink; // Sorvedouro, Destino
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);

    int count;
    private long id;

    public Produtor(long id, Pipe.SinkChannel sink, int count) {
        this.id = id;
        this.sink = sink;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                buffer.clear();
                buffer.putInt(i + 1);
                buffer.flip();
                this.sink.write(buffer);
                System.out.println(id + " Produce: " + i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
