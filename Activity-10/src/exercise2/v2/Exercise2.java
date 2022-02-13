package exercise2.v2;

/**
 * Implemente o problema do produtor/consumidor para uma estrutura com os
 * seguintes campos: número, simbolo, naipe, que representa uma carta de
 * baralho. A implementação deve possibilitar que após 10 cartas produzidas por
 * dois produtores, outros dois consumidores pegarão somente as maiores cartas.
 * Os produtores somente devem produzir mais cartas após os consumidores
 * removerem 3 cartas cada um. As cartas remanescentes podem continuar na
 * estrutura. Use a ordenação do baralho da menor para maior: A, 2, ..., 10, Q,
 * J, K.
 * 
 * Referências:
 * https://pt.wikipedia.org/wiki/Naipe
 * https://pt.wikipedia.org/wiki/Truco
 * https://www.baeldung.com/java-enum-values
 * https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
 * https://www.englishexperts.com.br/forum/as-cartas-do-baralho-em-ingles-t7705.html
 *
 * @author daniel
 */
public class Exercise2 {
    public static void main(String[] args) {
        ControlDistributionCards cards = new ControlDistributionCards();
        Producer distributor = new Producer(cards);
        Consumer players = new Consumer(cards);
        new Thread(players).start();
        new Thread(players).start();
        new Thread(distributor).start();
        new Thread(distributor).start();
    }
}
