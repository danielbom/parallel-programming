package exercise2;

public enum Naipe {
    DIAMONDS("diamonds"), // Ouros
    SPADES("spades"), // Espadas
    HEARTS("hearts"), // Copas
    CLUBS("clubs"); // Paus

    private String name;

    private Naipe(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
