package exercise2;

public class Card {
    private Naipe naipe;
    private int number;

    public Card(int number, Naipe naipe) {
        this.naipe = naipe;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
    public Naipe getNaipe() {
        return naipe;
    }
    
    @Override
    public String toString() {
        return String.format("Card(%d,%s)", number, naipe.toString());
    }
}
