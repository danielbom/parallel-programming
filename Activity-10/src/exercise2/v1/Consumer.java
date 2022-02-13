package exercise2.v1;

public class Consumer implements Runnable {
    private ControlDistributionCards cards;

    public Consumer(ControlDistributionCards cards) {
        this.cards = cards;
    }

    @Override
    public void run() {
        String name = Thread.currentThread()
                            .getName();
        while (cards.running) {
            while (!cards.consume) {
            }
            System.out.println("Consumer");

            for (int i = 0; i < 3; i++)
                System.out.println(name + " " + cards.get());

            cards.consume = false;
        }
    }
}
