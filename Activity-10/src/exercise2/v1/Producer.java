package exercise2.v1;

public class Producer implements Runnable {
    private ControlDistributionCards cards;

    public Producer(ControlDistributionCards cards) {
        this.cards = cards;
    }

    @Override
    public void run() {
        while (cards.running) {
            while (cards.consume) {
            }
            System.out.println("Produce");

            cards.add();
        }
    }
}
