package pipe_nio.producer_consumer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Consumer implements Runnable {
    Pipe.SourceChannel source; // Fonte, Origem
    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    
    public Consumer(Pipe.SourceChannel source) {
        this.source = source;
    }
    
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        while (true) {
            buffer.clear();
            try {
                source.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.flip();
            System.out.println(name + " Consume: " + buffer.getInt());
        }
    }
}
