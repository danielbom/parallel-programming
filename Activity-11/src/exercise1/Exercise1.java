package exercise1;

import java.io.IOException;
import java.nio.channels.Pipe;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Implementar o problema do produtor-consumidor usando Java NIO: Pipe,
 * Pipe.SinkChannel e Pipe.SourceChannel.
 * 
 * @author daniel
 *
 */
public class Exercise1 {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) 
            executor.execute(new Consumer(i, pipe.source()));
        
        for (int i = 0; i < 1; i++)
            executor.execute(new Produtor(i, pipe.sink(), 100));
    }
}
