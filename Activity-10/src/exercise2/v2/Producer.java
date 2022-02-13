package exercise2.v2;

public class Producer implements Runnable {
    private ControlDistributionCards cards;

    public Producer(ControlDistributionCards cards) {
        this.cards = cards;
    }

    @Override
    public void run() {
        while (cards.isRunning()) {
            cards.add();
        }
    }
}
