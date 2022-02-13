package exercise2.v2;

public class Consumer implements Runnable {
    private ControlDistributionCards cards;

    public Consumer(ControlDistributionCards cards) {
        this.cards = cards;
    }

    @Override
    public void run() {
        while (cards.isRunning()) {
            cards.get();
        }
    }
}
