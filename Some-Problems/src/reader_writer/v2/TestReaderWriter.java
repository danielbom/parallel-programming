package reader_writer.v2;

import java.util.Random;

public class TestReaderWriter {
    public static void main(String[] args) {
        ReaderWriter rw = new ReaderWriter();

        Random random = new Random();
        Runnable readers = () -> {
            String name = Thread.currentThread()
                                .getName();
            int time = random.nextInt(5000);
            System.out.println(name + ": Esperando para leitura " + time);
            try {
                Thread.sleep(time);
                rw.startRead();
                time += random.nextInt(2000) + 1000;
                System.out.println(name + ": Lendo " + time);
                Thread.sleep(time);
                System.out.println(name + ": Fim da leitura");
                rw.endRead();
            } catch (InterruptedException e) {
                // ...
                e.printStackTrace();
            }
        };

        Runnable writers = () -> {
            String name = Thread.currentThread()
                                .getName();
            int time = random.nextInt(3000);
            System.out.println(name + ": Esperando para escrita " + time);
            try {
                Thread.sleep(time);
                rw.startWrite();
                System.out.println(name + ": Escrevendo " + time);
                Thread.sleep(time);
                System.out.println(name + ": Fim da escrita " + time);
                rw.endWrite();
            } catch (InterruptedException e) {
                // ...
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(writers).start();
            new Thread(readers).start();
        }
    }
}
