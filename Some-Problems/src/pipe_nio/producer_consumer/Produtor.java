package pipe_nio.producer_consumer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Produtor implements Runnable {
    Pipe.SinkChannel sink; // Sorvedouro, Destino
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    
    int count;
    
    public Produtor(Pipe.SinkChannel sink, int count) {
        this.sink = sink;
        this.count = count;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            buffer.clear();
            buffer.putInt(i);
            buffer.flip();
            try {
                this.sink.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Produce: " + i);
        }
    }
}
