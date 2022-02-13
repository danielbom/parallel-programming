package exercise2.v1;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import exercise2.Card;
import exercise2.Naipe;

public class ControlDistributionCards {
    private static final ArrayList<Card> deck = new ArrayList<Card>();

    private PriorityBlockingQueue<Card> cards = new PriorityBlockingQueue<Card>(100,
            (a, b) -> a.getNumber() - b.getNumber());

    public volatile boolean running = true;
    public volatile boolean consume = false;
    public volatile int count = 0;

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
        if (count < 10 && running) {
            Card card = deck.remove(deck.size() - 1); // Pop
            cards.add(card);
            count++;
            System.out.println("Add " + running);
        } else {
            count = 0;
            consume = true;
            this.notifyAll();
        }
    }

    public synchronized Card get() {
        Card card = cards.poll();
        return card;
    }

    public synchronized boolean isEmpty() {
        return deck.isEmpty();
    }
}
