package exercise2.v2;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import exercise2.Card;
import exercise2.Naipe;

public class ControlDistributionCards {
    private static final ArrayList<Card> deck = new ArrayList<Card>();

    private PriorityBlockingQueue<Card> cards = new PriorityBlockingQueue<Card>(100,
            (a, b) -> b.getNumber() - a.getNumber());

    private volatile boolean running = true;
    private volatile int produced = 0;
    private volatile int consumed = 0;

    public ControlDistributionCards() {
        for (Naipe naipe : Naipe.values())
            for (int i = 1; i <= 13; i++)
                deck.add(new Card(i, naipe));

        // Shuffle
        for (int i = 0; i < deck.size(); i++) {
            Card card = deck.get(i);
            int j = (int) Math.floor(Math.random() * deck.size());
            deck.set(i, deck.get(j));
            deck.set(j, card);
        }
    }

    public synchronized void add() {
        running = deck.size() > 0;

        if (running) {
            if (produced < 10) {
                cards.add(deck.remove(deck.size() - 1));
                produced++;
                System.out.println("Add " + produced);
                if (produced == 10) {
                    consumed = 0;
                }
            }
        }
    }

    public synchronized Card get() {
        String name = Thread.currentThread()
                            .getName();
        if (running) {
            if (consumed < 6) {
                Card card = cards.poll();
                consumed++;
                if (card != null)
                    System.out.println(name + " " + card);
                if (consumed == 6) {
                    produced = 0;
                }
                return card;
            }
        }
        return null;
    }

    public boolean isRunning() {
        return running;
    }
}
